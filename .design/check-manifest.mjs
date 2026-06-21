import { readFileSync, existsSync } from "node:fs";
import { join } from "node:path";
const REPO = process.argv[2];
const m = JSON.parse(readFileSync(join(REPO, "journey.json"), "utf8"));
let bad = 0;
for (const s of m.solutions) {
  if (!existsSync(join(REPO, s.file))) { console.log("MISSING", s.file); bad++; }
}
console.log(bad ? bad + " MISSING" : "OK: all " + m.solutions.length + " files resolve");
console.log("with solutionType:", m.solutions.filter((s) => s.solutionType).length + "/" + m.solutions.length);
console.log("relocated (originalFile):", m.solutions.filter((s) => s.originalFile).length);
console.log("with strategyComment:", m.solutions.filter((s) => s.strategyComment).length);
console.log("--- sample relocated entry ---");
console.log(JSON.stringify(m.solutions.find((s) => s.originalFile), null, 2));
console.log("--- sample entry with strategyComment ---");
console.log(JSON.stringify(m.solutions.find((s) => s.strategyComment), null, 2));
