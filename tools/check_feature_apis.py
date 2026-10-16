"""Verify read-only SSCA and all three optional viewer APIs against real 1.20.1 jars."""
import argparse
import zipfile
from check_ssc_api import members


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    for key in ("ssca192", "ssca110", "jei", "rei", "emi"):
        parser.add_argument("--" + key, required=True)
    args = parser.parse_args()
    checks = 0
    def expect(jar, cls, method, descriptor=None):
        nonlocal checks
        with zipfile.ZipFile(jar) as z:
            data = members(z.read(cls.replace(".", "/") + ".class"))
        assert any(n == method and flags & 1 and (descriptor is None or d == descriptor) for n, d, flags in data), (cls, method, descriptor)
        checks += 1
    base = "net.jackcooper.shapeShifterCurseAddon."
    stack = "Lnet/minecraft/class_1799;"
    player = "Lnet/minecraft/class_1657;"
    spell = "Lnet/jackcooper/shapeShifterCurseAddon/spell/Spell;"
    for jar in (args.ssca192, args.ssca110):
        expect(jar, base + "client.SpellcastClient", "getEquippedBook", "()" + stack)
        expect(jar, base + "client.SpellcastClient", "getSelectedSlot", "()I")
        for method in ("getLevel", "getMana", "getMaxMana", "getSlotCount", "getExpTen"):
            expect(jar, base + "spell.SpellbookData", method, "(" + stack + ")I")
        expect(jar, base + "spell.SpellbookData", "getScroll", "(" + stack + "I)" + stack)
        for method in ("getCastLevel", "getLevel"):
            expect(jar, base + "spell.ScrollData", method, "(" + stack + ")I")
        expect(jar, base + "spell.ScrollData", "getSpell", "(" + stack + ")" + spell)
        expect(jar, base + "spell.SharedSpellCooldowns", "getEffectiveCooldownEnd", "(" + player + stack + ")J")
        expect(jar, base + "spell.SpellNumbers", "finalManaCost", "(" + spell + stack + player + "I" + stack + ")I")
        expect(jar, base + "evolution.RegEvolutionComponent", "EVOLUTION")
        for method in ("getRoute", "getPoints", "getEvoLevel", "isUnlocked"):
            expect(jar, base + "evolution.EvolutionComponent", method)
        for method in ("INSTANCE", "all", "getRoute", "getRouteByStartForm"):
            expect(jar, base + "evolution.EvolutionRegistry", method)
        for field in ("nodes", "enabled", "startForm", "displayNameKey"):
            expect(jar, base + "evolution.EvolutionRoute", field)
        for field in ("id", "prereqs", "cost", "branch", "autoUnlock", "nameKey"):
            expect(jar, base + "evolution.EvolutionNode", field)
        for method in ("crafting", "brewing", "processes"):
            expect(jar, base + "compat.recipeviewer.SscViewerRecipes", method)
    for cls, method in (("mezz.jei.api.runtime.IJeiRuntime", "getRecipesGui"),
                         ("mezz.jei.api.runtime.IJeiRuntime", "getJeiHelpers"),
                         ("mezz.jei.api.helpers.IJeiHelpers", "getFocusFactory"),
                         ("mezz.jei.api.recipe.IFocusFactory", "createFocus"),
                         ("mezz.jei.api.runtime.IRecipesGui", "show"),
                         ("mezz.jei.api.IModPlugin", "onRuntimeAvailable"),
                         ("mezz.jei.api.IModPlugin", "onRuntimeUnavailable")):
        expect(args.jei, cls, method)
    expect(args.rei, "me.shedaniel.rei.api.common.util.EntryStacks", "of", "(" + stack + ")Lme/shedaniel/rei/api/common/entry/EntryStack;")
    for method in ("builder", "addRecipesFor", "open"):
        expect(args.rei, "me.shedaniel.rei.api.client.view.ViewSearchBuilder", method)
    expect(args.emi, "dev.emi.emi.api.stack.EmiStack", "of", "(" + stack + ")Ldev/emi/emi/api/stack/EmiStack;")
    expect(args.emi, "dev.emi.emi.api.EmiApi", "displayRecipes", "(Ldev/emi/emi/api/stack/EmiIngredient;)V")
    print(f"Compass feature API contracts PASS: {checks} checks; both SSCA branches, JEI, REI and EMI. GUI/multiplayer not started.")


if __name__ == "__main__":
    main()
