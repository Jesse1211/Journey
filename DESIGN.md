# DESIGN.md — Journey repo refactor (Phase A)

Decision ledger for making the `Journey` repo generic & machine-parseable, so
`jesse1211.github.io` can later consume it as a git submodule (Phase B, NOT in
scope here). Locked in a design session 2026-06-21. Build agents cite ADRs; they
do not re-litigate them.

## Scope

**In scope (Phase A, this repo only):**
- Normalize the `Algos/` solution tree into a predictable shape.
- Emit a machine-readable manifest (`journey.json`).
- Parse `ALGO_README.md`'s daily log into a structured timeline (`timeline.json`).
- Migrate CodeBlog's 9 strategy guides into `Journey/guides/`.

**Out of scope (Phase B, the portfolio repo — later):**
- Adding Journey as a submodule of `jesse1211.github.io`.
- Any React/terminal UI to render this content.

## Grounding rule (held all session)
"Not built" ≠ "not supported" ≠ "design assumes the opposite." We describe only
verifiable facts about the current repo. **No invented metadata** — see ADR-002.

---

## Domain model

- **Bounded context:** `Journey` — Jesse's self-study archive (LeetCode solutions +
  strategy guides + a training journal). Self-contained; the portfolio only *reads*
  its published artifacts (the manifest, the timeline, the guides), never reaches
  into raw files.
- **Boundary / contract:** Phase B depends ONLY on `journey.json`, `timeline.json`,
  and `guides/*.md`. The raw `.java` tree is an implementation detail behind that
  contract. Changing folder layout later must not break the contract as long as the
  manifest still resolves.
- **Aggregate root:** the **Solution**, NOT the problem. One LeetCode problem may be
  solved several ways; **each solution is classified by its own code**, so the same
  problem id legitimately appears under multiple topics (e.g. 200 in BFS + DFS, 322 in
  DP + BFS). The **manifest** (`journey.json`) is the index over all solutions: every
  `.java` file appears exactly once, keyed by file path, never deduped by problem id.
- **Entities:** `Solution` (identity = the file; carries `id`, a code-derived
  `solutionType`, optional `nameFragment`; its folder/path is a mutable attribute).
  `Guide` (identity = slug). `TimelineEntry` (identity = day label, e.g. `Day76.2`).
- **Value objects:** `topic`, `subtopic` (strings), the inline strategy comment.
- **Ubiquitous language:** *solution* = one `.java` file for one LeetCode problem.
  *topic* = top-level `Algos/` folder. *subtopic* = nested folder (BFS/Tree).
  *guide* = a strategy writeup (migrated from CodeBlog). *timeline* = the parsed
  daily training log. *manifest* = `journey.json`.

## Invariants
- **INV-1 (compiles):** after restructuring, every `.java` file's `package`
  declaration matches its new folder path (or is removed consistently). Java still
  compiles. 291/343 files currently declare a package tied to their folder.
- **INV-2 (manifest completeness):** every solution file ↔ exactly one manifest
  entry; every manifest `file` resolves. No orphans, no dangling paths. Duplicate
  problem ids across topics are EXPECTED and preserved — never collapsed (ADR-008).
- **INV-6 (classification is code-derived):** each solution's `solutionType` comes
  from analyzing its actual code (algorithm/data structure used), not the problem
  name. Auto-relocation only moves a file when its code-derived type contradicts its
  current folder; the original path is recorded (ADR-009).
- **INV-3 (no data loss):** no solution code is deleted or altered in meaning;
  restructuring is move + (package-line) edit only. Counts before == after.
- **INV-4 (honest metadata):** the manifest contains only fields derivable from
  existing files/folders/READMEs. No fabricated difficulty/title/url. (ADR-002)
- **INV-5 (git history preserved):** file moves use `git mv` so history follows.

---

## ADRs (locked decisions)

- **ADR-001 — Restructure files AND emit a manifest** (not one or the other).
  Predictable paths *and* a rich index. Rationale: portfolio parse is trivial off a
  single manifest; predictable paths keep the repo navigable by humans.
- **ADR-002 — No invented metadata.** Manifest fields = { id (from filename), topic
  (folder), subtopic (nested folder if any), nameFragment (filename text after the
  id, if any), file (path), strategyComment (the leading block/`//` comment in the
  file, if present) }. Difficulty, official title, and LeetCode URL are NOT in the
  source → excluded. Rationale: user chose honesty over richness; avoids a 343-row
  hallucination surface.
- **ADR-003 — Keep subfolders; normalize names only.** Preserve meaningful English
  subfolders (BFS/{FromCenter,ToCenter,Tree}, BacktrackingDFS/{List,Tree,Trie},
  BinarySearch/{BinarySearch,BinarySearchExclusive,BinarySearchInclusive},
  DataStructure/{Heap,Map,Stack,Tree,Trie}). Translate the only 3 Chinese subfolders,
  all under `TwoPointer/`: `同向`→`SameDirection`, `反向`→`Opposite`,
  `两个array`→`TwoArrays`. Rationale: subfolders encode real categorization worth
  keeping; only the Chinese names block a generic parser/ASCII paths.
- **ADR-004 — Fix packages on move.** When a folder is renamed/translated, update the
  `package` line in every affected file to match (enforces INV-1). Files with no
  package decl (52 of them) stay as-is.
- **ADR-005 — Parse the daily log into a timeline.** `ALGO_README.md` is a 110+-day
  training journal, not a problem index. Emit `timeline.json` = ordered list of
  `{ day, date, section, note }`. The raw README stays. Rationale: the journal IS the
  "leetcode journey" narrative the portfolio will tell.
- **ADR-006 — Migrate guides, keep CodeBlog.** Copy CodeBlog's 9 markdown strategy
  guides into `Journey/guides/`, converting Hugo TOML front-matter (`+++ … +++`) to a
  minimal YAML/markdown form. Do NOT delete or modify the CodeBlog repo (kept as
  backup). Convert the `{{< notice … >}}` Hugo shortcodes to plain markdown
  blockquotes. Rationale: one submodule for Phase B; CodeBlog retained for safety.
- **ADR-007 — Manifest is generated, not hand-written.** `journey.json` is produced by
  a script/agent from the tree, so it stays in sync and is re-runnable.
- **ADR-008 — Solution is the unit; duplicates are kept.** Aggregate root = solution,
  not problem. The same problem id solved two ways = two manifest entries, each with
  its own `solutionType`. Never dedupe by id. Rationale: a problem's "type" is a
  property of the *solution*, not the problem — the duplicates ARE the signal (14 real
  duplicate ids exist today, e.g. 200/322/300/567). User decision.
- **ADR-009 — Classify by code; auto-relocate misfiles.** A leetcode-domain agent
  reads each solution's code and assigns `solutionType` from the algorithm/data
  structure actually used. If that contradicts the file's current folder (e.g. a DP
  solution sitting under `Array/`), the agent **moves the file** to the correct topic
  folder (via `git mv`, fixing its package per ADR-004) and records the original path
  in the manifest as `originalFile`. Still honest metadata — derived from existing
  code, nothing invented (consistent with ADR-002). User decision: auto-relocate.

## Open questions
- **OQ-01** — Manifest format: single `journey.json` vs per-topic files. *Default: one
  `journey.json` at repo root + the timeline as `timeline.json`.* Revisit only if the
  portfolio parser (Phase B) wants per-topic splitting.
- **OQ-02** — Whether to link each guide to its topic in the manifest (e.g.
  `guides/dfs.md` ↔ topic `BacktrackingDFS`/`BFS`). *Default: include a best-effort
  `guideSlug` on topics where the mapping is unambiguous; leave null otherwise. Do not
  invent links.* (consistent with ADR-002)
- **OQ-03** — Strategy guide `id → problem` cross-refs inside guides reference problem
  numbers; do we resolve them to manifest entries? *Default: leave as-is for Phase A;
  Phase B can resolve at render time.*

---

## Task DAG (Phase A)

| Task | Depends on | Spec | Acceptance gate |
|------|-----------|------|-----------------|
| **T1 — Inventory & baseline** | — | Produce a baseline list of every `.java` under `Algos/` with its current path + whether it has a `package` decl + the package value. Save as `.design/baseline.tsv`. Count total files. | `.design/baseline.tsv` exists; row count == `find Algos -name '*.java' \| wc -l` (343 at design time). |
| **T2 — Translate the 3 Chinese subfolders** | T1 | `git mv` `TwoPointer/同向`→`SameDirection`, `反向`→`Opposite`, `两个array`→`TwoArrays`. Update the `package` line in every moved file to the new path (ADR-004). No other folders touched. | No path under `Algos/` contains non-ASCII (`find Algos -type d \| grep -P '[^\x00-\x7f]'` empty). Every moved file's `package` matches its new folder. |
| **T3 — Normalize filenames** | T1 | Within each folder, ensure each solution filename starts with the numeric problem id (already true for all 343 — verify, fix any exception). Do NOT strip name fragments (`213Rob2.java` stays). Record `{id, nameFragment}` per file. | Every `.java` filename matches `^[0-9]+.*\.java$`. Baseline count preserved (INV-3). |
| **T3b — Classify by code & auto-relocate** | T2, T3 | A **leetcode-domain agent** reads every solution's code and assigns `solutionType` from the algorithm/data structure actually used (ADR-009). Where the type contradicts the current folder, `git mv` the file to the correct topic (fix package per ADR-004) and record `originalFile`. Duplicate problem ids across topics are preserved, never merged (ADR-008). | Every solution has a code-derived `solutionType`; every relocation logged with `originalFile`; baseline file count preserved (INV-3); INV-1 holds after moves. |
| **T4 — Generate `journey.json`** | T3b | Script/agent walks `Algos/`, emits one entry **per solution file** (NOT per problem): `{ id, topic, subtopic?, nameFragment?, solutionType, file, originalFile?, strategyComment? }` per ADR-002/008/009. Generated, re-runnable (ADR-007). | `journey.json` valid JSON; entry count == solution file count (INV-2, dups kept); every `file` resolves; no field outside the allow-list; duplicate ids present where they exist in the tree. |
| **T5 — Generate `timeline.json`** | T1 | Parse `ALGO_README.md` daily log into ordered `[{ day, date, section, note }]` (ADR-005). Raw README untouched. | `timeline.json` valid JSON; entry count == number of `- [x] Day…` lines in ALGO_README.md; dates parse. |
| **T6 — Migrate guides** | — | Copy the 9 CodeBlog guides (`content/algorithm/*.md` incl. `pending/`) into `Journey/guides/`, convert `+++`TOML→YAML front-matter, `{{< notice … >}}`→blockquotes (ADR-006). CodeBlog repo untouched. | `guides/` has 9 `.md`; none contain `+++` or `{{<`; each has a `title`. CodeBlog repo `git status` clean/unchanged. |
| **T7 — Compile/sanity check** | T2, T3, T4 | Verify the Java still compiles where it did before (or, if no build system, that every `package` line matches its folder path — INV-1). Re-run manifest generator; confirm idempotent. | INV-1 check passes for all 343 files; re-running T4 produces byte-identical `journey.json`. |

## Global commands
- Inventory: `find Algos -name '*.java'`
- Non-ASCII path check: `find Algos -type d | grep -P '[^\x00-\x7f]'`
- `TEST_CMD`: there is no test runner; the gate is the per-task structural check above.
- `LINT_CMD`: n/a for this repo (no JS/TS). Java "lint" = INV-1 package/path match.
- Manifest regen (idempotency): the T4 generator script, run twice, diffed.

---

## Phase B design draft (archived — NOT built this turn)

Phase B wires Journey into `jesse1211.github.io` as a git submodule, migrates
JesseBlog, and renders everything in a Claude-designed UI that **extends the existing
fake-glass-terminal metaphor** (append-only history; `ls -la <dir>/` + chip nav; no
`cd`; glass panels + cyan `hsla(180,100%,80%)` + scanlines + Zdog star bg; `term-mono`).

- **PB-1 — `leetcode/` + `blog/` categories.** `ls categories/` gains `leetcode/`
  (Journey archive) and `blog/` (JesseBlog knowledge md). Same chip-driven `ls` model
  as today's experience/education/about.
- **PB-2 — Drill by `ls`.** `ls leetcode/` → topics + `guides/` + `journey.log`;
  `ls leetcode/BFS/` → a chip per solution rendered as `$ less 200.java`.
- **PB-3 — View solutions with `less` + modal (REUSE `DescriptionModal` pattern).**
  Clicking `$ less <file>` opens a `Modal`/`ModalDialog`/`GlassPanel` overlay (title
  `$ less BFS/200.java`, `ModalClose`), containing: header line (`#id · name ·
  type:<solutionType>`), the file's inline Chinese strategy comment, syntax-highlit
  Java scrolling **inside the overlay**, and for multi-solution problems an
  "also solved as: [ $ less DFS/200.java ]" chip. Rationale: long code must not bloat
  the append-only terminal history — overlays keep the stream clean. (User decision:
  `less`-popup, not inline `cat`.)
- **PB-4 — Guides via `less` too.** `$ less guides/BFS.md` → same modal, renders the
  migrated markdown.
- **PB-5 — `journey.log` stays inline.** The daily-log timeline renders git-log style
  (`* Day100  11/25  …`) directly in the terminal stream — it's naturally append-only,
  so it does NOT pop. The literal "leetcode journey" narrative.
- **PB-6 — JesseBlog migration.** Backend/Frontend/Courses md migrated into the
  portfolio under `blog/`, viewed with the same `less` modal. Separate content line
  from the leetcode archive.
- **Data contract:** the UI reads ONLY `journey.json`, `timeline.json`, `guides/*.md`
  from the submodule — never the raw `.java` tree directly.

This draft is for reference; Phase B gets its own design pass + build.

## Last human checkpoint
After this DESIGN.md is confirmed, Phase A may run unattended. Phase B (submodule +
portfolio UI) is a separate future design + build.
