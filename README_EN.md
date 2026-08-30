<div align="center">

# ✦ Shape Shifter's Compass

### In-Game AI Assistant · Built for Shape Shifter's Curse (SSC) & Addon (SSCA)

Fabric 1.20.1 · Client-side Mod · OpenAI-Compatible API · `1.0.0`

</div>

---

## What Is This

**Shape Shifter's Compass** is a client-side mod that brings a large language model into Minecraft. It exists as a "dedicated assistant" for **Shape Shifter's Curse and its addon** — it can **read your inventory, equipment, trinkets, crafting grid, current form and skill cooldowns**, **search the built-in SSC/SSCA knowledge base** to answer questions about form evolution, Lunar Marrow Ring, whitelist and other mechanics, and (when **cheats are enabled**) help you **execute commands, locate structures, and give items**. It also uses the **same algorithm as AppleSkin** to precisely calculate a food's hunger and saturation, telling you whether "feeding a Gold Carrot to your Familiar actually does anything".

> Author: MangZai-120 · License: MIT · Environment: Client-side only

---

## Core Features

- 🧭 **HUD Floating Ball + Small Window**: Always on screen without blocking gameplay; press **K** to bring up the interactive window, click the "▢" in the title bar to expand the full window.
- 🛠️ **15 Built-in Tools**: The AI can read your game state, get precise food values, search the knowledge base, perform web search, and (with cheats) execute commands — **reads only your own data; cheat tools are server-side permission enforced**.
- 🍗 **AppleSkin-Style Food Values**: `get_food_info` / `get_inventory` / `get_equipment` all include a food's hunger value, saturation restore (`hunger × saturation coefficient × 2`), whether it's edible right now, and status effects; with Apoli installed, the current form's `modify_food` modifier is applied.
- 📚 **SSC/SSCA Knowledge Base**: Built-in entries for forms, evolution, mechanics, and commands (`knowledge/core/`); extend by simply dropping `.md` files into the directory.
- 💬 **WeChat-Style Bubble Window**: Local typewriter effect, scrollbar, session list, click your own message to inline-edit and regenerate, ↑↓ history navigation.
- 🔒 **Anti-Jailbreak Identity**: The system prompt locks the Compass role — no matter what the player says, it won't break character.
- ⚙️ **Multi-Vendor Presets**: OpenAI / z.ai GLM / DeepSeek / Moonshot / OpenRouter / OpenCode Zen / Custom, one-click switching (**DeepSeek V4 Flash recommended**).
- 🎨 **Visual Layout Editor**: Drag the floating ball anywhere on screen; fine-tune font / opacity / width / height with sliders.
- 🔇 **Command Feedback Suppression**: AI command feedback is written only to the backend log, intercepted by the ChatHud Mixin, and never pollutes the player's chat.

---

## Quick Start

1. Drop the jar into `mods/` (requires Fabric API; Trinkets and SSC/SSCA are soft dependencies — install them to unlock the corresponding tools).
2. Enter the game and press **K** to open the window → click the hint text or title bar to enter **⚙ Settings** → choose an AI vendor (DeepSeek recommended) → enter your API Key → Save.
3. Back in the window, ask: "What form am I in right now?" / "How do I evolve with the Lunar Marrow Ring?" / "Does feeding a Gold Carrot to a Familiar work?" / "Give me a stack of Iron Ingots" (requires cheats).

The default hotkey is **K** (`key.ssc_compass.open`, rebindable in Controls). If K is intercepted while a container screen (inventory / crafting table) is open, a physical key edge-detection fallback brings up the window overlaid on the original screen.

---

## Built-in Tools

The AI auto-invokes the following tools as needed (function calling). **Read-only** tools are available by default; **cheat** tools require cheats to be enabled in settings **and** OP permission (`cheatEnabled && hasPermissionLevel(2)`, server-side enforced, client cannot tamper), and are strictly validated at the tool level — they cannot be bypassed via prompt. All tools operate only on the player's own data.

| Tool | Category | Description |
|------|----------|-------------|
| `get_inventory` | Read-only | 36-slot inventory items and counts; food items include AppleSkin-style values |
| `get_equipment` | Read-only | Equipment (helmet/chestplate/leggings/boots) + off-hand; main & off-hand food items include values |
| `get_crafting_grid` | Read-only | Current crafting grid (2×2 / 3×3) layout and result; requires the corresponding screen to be open |
| `query_recipe` | Read-only | Query registered recipes by item ID / Chinese or English name (first 5) |
| `get_trinkets` | Read-only | Trinkets accessory slots (requires Trinkets installed) |
| `get_form_status` | Read-only | Current SSC form + all Apoli resources (CD/mana/energy; requires SSC) |
| `query_knowledge` | Read-only | Search the built-in SSC/SSCA knowledge base (forms/evolution/mechanics/commands) |
| `get_player_status` | Read-only | Health/hunger/saturation/armor/XP/coordinates/dimension/status effects/attributes |
| `web_search` | Read-only · Async | Web search (web / Minecraft Wiki / Wikipedia) |
| `get_seed` | Read-only | World seed (read directly in single-player; on a server, prompts `/seed`) |
| `list_mods` | Read-only | List loaded mods' id / name / version |
| `get_food_info` | Read-only | Precise values for any food (AppleSkin's algorithm; applies form modifier when Apoli is present) |
| `locate_structure` | 🔒Cheat · Async | Locate the nearest structure or biome |
| `run_command` | 🔒Cheat · Async | Execute Minecraft commands on the whitelist (dangerous commands like op/stop/ban are always rejected) |
| `set_player_data` | 🔒Cheat · Async | Modify max health / full heal / full hunger / set XP level |

> Command feedback from cheat tools is suppressed by `ChatHudMixin` — **written only to the backend log, not shown in the player's chat**; the `/locate` coordinate feedback is first parsed by `LocateTool` before being swallowed.

---

## Configuration

Config file: `config/ssc_compass.json`, or press **⚙** in-game to open the ConfigScreen for visual editing (apiKey is masked with `*` while rendering; the cheat toggle is visible to OP only).

**AI Connection:** `provider` / `baseUrl` / `apiKey` / `model` / `temperature`(0.7) / `maxTokens`(1024) / `maxHistory`(20)

**HUD Layout:** `hudBallX/Y`(-1 = right side/center by default) / `hudBoxX/Y`(4,4) / `hudBoxWidth`(160) / `hudBoxHeight`(112) / `hudVisible`(true) / `hudBgAlpha`(128) / `hudFontPct`(75) / `chatFontPct`(100)

**Other:** `cheatEnabled`(false) — whether cheat commands are enabled (only OP can toggle in the UI)

### Supported AI Vendor Presets

| Vendor | baseUrl | Candidate Models |
|--------|---------|------------------|
| OpenAI | `api.openai.com/v1` | gpt-5.4 / gpt-5.5-mini |
| z.ai (GLM) | `api.z.ai/api/paas/v4` | glm-5 / glm-5.1 |
| **DeepSeek** (recommended) | `api.deepseek.com` | **deepseek-v4-flash** / deepseek-v4-pro |
| Moonshot (Kimi) | `api.moonshot.cn/v1` | kimi-k2.6 / kimi-k3 |
| OpenRouter | `openrouter.ai/api/v1` | claude-sonnet-4.6 / gpt-5.2 / deepseek-chat |
| OpenCode Zen | `opencode.ai/zen/v1` | glm-5.2 / kimi-k2.6 / deepseek-v4-flash |
| Custom | custom | custom |

Any **OpenAI-compatible** endpoint can be connected (just fill in `baseUrl` + `apiKey` + `model`).
> DeepSeek V4 Flash is recommended.

---

## UI Overview

- **ChatScreen (Full Window)**: Left-side session list (create/switch/delete, hover to see token & byte usage), right-side bubble conversation (your messages blue-right, AI gray-green-left), typewriter, scrollbar, **click your own message to inline-edit → truncate subsequent conversation and regenerate**, ↑↓ history navigation, compact/expand toggle.
- **CompassOverlayScreen (Small Window)**: Opened with K, semi-transparent, mouse visible, doesn't block the full screen; type directly for Q&A with answers shown in the HUD box; when no key is configured, shows three lines — "Not yet configured..." + "→ Click to go to settings" + "DeepSeek V4 Flash recommended" — clicking goes straight to settings.
- **CompassHud (HUD)**: Persistent floating ball + small box, doesn't intercept player actions; also drawn on top of container screens; content font size / background opacity adjustable; three dynamic states — not configured / thinking / has answer.
- **ConfigScreen (Settings)**: Choosing a vendor auto-fills address and model, enter Key (masked), cheat toggle (visible to OP only), layout editor entry.
- **CompassEditorScreen (Layout Editor)**: Drag the floating ball anywhere (the small box follows), real-time preview of font / opacity / width / height sliders.

---

## System Prompt Customization

Compass's system prompt is deeply customized for SSC/SSCA, with key rules:

- **Anti-Jailbreak Identity**: No matter how it's persuaded / hypnotized / claimed to have higher authority, it always stays in the Compass assistant role.
- **Self-Introduction Order**: When asked "what can you do", it first explains its own features, then its SSC-specific optimization.
- **Form Introduction Boundary**: When asked about SSC forms, only answers SSC; when asked about SSCA forms, only answers SSCA — no cross-contamination; when unspecified, first runs `list_mods` to confirm.
- **Form Count Questions**: When asked "how many forms are there in total", it answers dynamically — with SSC installed it answers the vanilla count, with SSCA it answers the addon count, with other addons like XU it supplements with `web_search` and declares the source.
- **Food Judgment**: Any food value question must call `get_food_info` (live registry data) — never answers from memory; for saturation-immune forms (Familiar-Fox series), it switches to Compressed Energy Potions instead of normal food.
- **Transform Command**: `/shape_shifter_curse set_form @s <formId>` (no animation) or `transform_to_form` (with animation); the formId carries a namespace and must be looked up in the knowledge base for the exact name.

---

## Knowledge Base Extension

The knowledge base lives in `assets/ssc_compass/knowledge/core/` — plain Markdown files, split into entries by `##` second-level headings. Existing files: `overview.md` / `forms.md` / `commands.md`. To extend, simply add `.md` files to that directory; restart and they'll be searchable by `query_knowledge`.

---

## Dependencies

- **Required**: Fabric Loader ≥0.16, Fabric API, Minecraft 1.20.1, Java ≥17
- **Soft dependencies** (install to unlock corresponding tools): Trinkets (accessory slots), Shape Shifter's Curse / SSCA (forms, Apoli resources, knowledge base, food modifiers)
- **Optional**: Simple Voice Chat (voice feature reserved, Phase 3 in development)

---

## Mixins

| Mixin | Purpose |
|-------|---------|
| `ChatHudMixin` | Intercepts `ChatHud.addMessage`, swallows feedback within the command-feedback suppression window (first fed to LocateTool to ensure coordinate parsing) |
| `HandledScreenAccessor` | Exposes `HandledScreen`'s x/y/backgroundWidth (for layout positioning) |

---

## License

MIT. Source: <https://github.com/MangZai-120/Shape-Shifter-s-Compass>

<div align="center">

**✦ Compass · Your Personal Shape Shifter's Curse Guide ✦**

</div>
