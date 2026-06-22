# Journey

The single source of truth for all of my content — consumed as a git
submodule by the `jesse1211.github.io` portfolio (the render layer).
Edit content here; the site renders it.

## Layout

| Path | What |
|---|---|
| `Algos/<Topic>/*.java` | LeetCode solutions — indexed by `journey.json` |
| `guides/*.md` | Algorithm strategy guides — `guides/index.json` |
| `notes/<Section>/*.md` | Knowledge notes (Backend / Frontend / Courses / DevOps / MLs) — `notes/index.json` |
| `portfolio/*.json` | Bilingual experience / education / intro (+ `images/`) |
| `*_README.md` | Daily logs → parsed into `timeline.json` (algos) and `ai-timeline.json` (AI) |

## Regenerating indexes

Generators live in `.design/`:

```bash
node .design/gen-manifest.mjs       # journey.json (solutions)
node .design/gen-notes-index.mjs    # notes/index.json
node .design/gen-timeline.mjs       # timeline.json (from ALGO_README.md)
node .design/gen-ai-timeline.mjs    # ai-timeline.json (from AI_README.md)
```

After changing content + indexes: push here, then in the portfolio
`git submodule update --remote --merge src/journey-content` and commit
the bumped pointer to deploy.

---

> 2024, 2025 are not my best year, but I learnt a lot.
>
> You will never feel ready — ready is not a feeling but a decision.
