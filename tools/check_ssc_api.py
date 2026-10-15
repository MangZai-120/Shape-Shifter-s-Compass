"""Read actual release class files without loading Minecraft or optional dependencies."""
import argparse
import struct
import zipfile


def members(data):
    offset = 0
    def read(fmt):
        nonlocal offset
        value = struct.unpack_from(">" + fmt, data, offset)[0]
        offset += struct.calcsize(">" + fmt)
        return value
    assert read("I") == 0xCAFEBABE
    read("H"); read("H")
    pool = [None] * read("H")
    i = 1
    while i < len(pool):
        tag = read("B")
        if tag == 1:
            length = read("H")
            pool[i] = data[offset:offset + length].decode("utf-8", errors="replace")
            offset += length
        elif tag in (3, 4, 9, 10, 11, 12, 17, 18):
            offset += 4
        elif tag in (5, 6):
            offset += 8
            i += 1
        elif tag in (7, 8, 16, 19, 20):
            offset += 2
        elif tag == 15:
            offset += 3
        else:
            raise AssertionError(f"Unknown constant pool tag {tag}")
        i += 1
    read("H"); read("H"); read("H")
    interface_count = read("H")
    offset += interface_count * 2
    result = []
    for _ in range(2):  # fields then methods
        for _ in range(read("H")):
            flags, name, desc = read("H"), pool[read("H")], pool[read("H")]
            result.append((name, desc, flags))
            for _ in range(read("H")):
                read("H")
                length = read("I")
                offset += length
    return result


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    for key in ("ssc192", "ssc110", "apoli290", "apoli292", "cca"):
        parser.add_argument("--" + key, required=True)
    args = parser.parse_args()
    checks = 0
    def expect(jar, cls, name, desc):
        nonlocal checks
        with zipfile.ZipFile(jar) as archive:
            actual = members(archive.read(cls.replace(".", "/") + ".class"))
        assert any(n == name and d == desc and flags & 1 for n, d, flags in actual), (cls, name, desc)
        checks += 1
    prefix = "net.onixary.shapeShifterCurseFabric."
    player, identifier = "Lnet/minecraft/class_1657;", "Lnet/minecraft/class_2960;"
    form = "Lnet/onixary/shapeShifterCurseFabric/player_form/PlayerFormBase;"
    expect(args.ssc192, prefix + "player_form.ability.RegPlayerFormComponent", "PLAYER_FORM", "Ldev/onyxstudios/cca/api/v3/component/ComponentKey;")
    expect(args.ssc192, prefix + "player_form.ability.PlayerFormComponent", "getCurrentForm", "()" + form)
    expect(args.ssc192, prefix + "player_form.PlayerFormBase", "FormID", identifier)
    expect(args.ssc110, prefix + "player_form.utils.FormUtils", "getPlayerForm", "(" + player + ")Lnet/onixary/shapeShifterCurseFabric/player_form/IForm;")
    expect(args.ssc110, prefix + "player_form.IForm", "getFormID", "()" + identifier)
    for jar in (args.ssc192, args.ssc110):
        expect(jar, prefix + "mana.ManaUtils", "getManaComponent", "(" + player + ")Lnet/onixary/shapeShifterCurseFabric/mana/ManaComponent;")
        for method, desc in (("getMana", "()D"), ("getMaxMana", "()D"), ("getManaTypeID", "()" + identifier)):
            expect(jar, prefix + "mana.ManaComponent", method, desc)
    for jar in (args.apoli290, args.apoli292):
        for cls, name, desc in (("component.PowerHolderComponent", "getPowers", "(Ljava/lang/Class;)Ljava/util/List;"),
                                 ("power.Power", "getType", "()Lio/github/apace100/apoli/power/PowerType;"),
                                 ("power.PowerType", "getIdentifier", "()" + identifier),
                                 ("power.VariableIntPower", "getValue", "()I"),
                                 ("power.VariableIntPower", "getMax", "()I"),
                                 ("power.CooldownPower", "getRemainingTicks", "()I"),
                                 ("power.CooldownPower", "cooldownDuration", "I")):
            expect(jar, "io.github.apace100.apoli." + cls, name, desc)
    expect(args.cca, "dev.onyxstudios.cca.api.v3.component.ComponentKey", "get", "(Ljava/lang/Object;)Ldev/onyxstudios/cca/api/v3/component/Component;")
    print(f"SSC API contract PASS: {checks} actual class-file signatures; SSC 1.9.2 / 1.10.0, Apoli 2.9.0 / 2.9.2, CCA.")


if __name__ == "__main__":
    main()
