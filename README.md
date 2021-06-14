Ever wanted to show more support for your favorite team and mascot? Then have no fear because Mascotmon is here! 
This simulator allows mascots to battle each other to see which is the strongest one of all. 
The mascots have a specific Type (fire, water, ground, or normal) and three stats that consist of: attack, defense, and health.
The battlefield's weather as well as Type advantages may have positive or negative effects on the attack or defense stats of certain types.

# Code Review Defect List
> Reviewer: Ryan Meneses
> GH Repo: https://github.com/hiyaryan/ser316-summer2021-C-rmenese1/tree/Review

| ID #  |       Location [File/Line Number]       | Problem Description |      Problem [Category/Severity]     |
|---|---|---|---|                
| 1  |  ALL  | No source code file has a file banner comment present.  | CG/LOW  |
| 2  |  ALL  | No source code file has a class banner comment present.  | CG/LOW  |
| 3  |  ALL  | No constructor has a method banner comment present.  | CG/LOW  |
| 4  | Mascotmon/148 & Environment/45 | Enums are not in all CAPS  | CG/LOW  |
| 5  | type  | Class name is not capitalized  | CG/LOW |
| 6  | Mascotmon10 & Mascotmon/59 | Variable names are not camel cased  | CG/LOW  |
| 7  |  ALL  | No attributes are private  | CG/MJ |
| 8  | Mascotmon/8-9 | Literal values are not final  | CG/MJ  |
| 9  | Description/11-17 & Type/14-18 | Complex statements do not include explicit {} | CG/MJ  |
| 10  | BattleScenario/60-93  | Duplicate code  | CS/LOW  |
| 11  | Mascotmon/76-142  | Features Attack constructor extensively  | CS/LOW  |
| 12  | Attack  | Lazy class  | CS/LOW |
| 13  | Mascotmon/4-7 & Stats/3-5 & Type/4 | Attributes are directly accessed by BattleScenario (accessing Mascotmon) and Mascotmon (accessing Stats and Type) with a potential hazard for being overwritten  | FD/MJ  |
| 14  | BattleScenario/144-146  | calculateDamage method is implemented incorrectly   | FD/BR  |

Category:	CS – Code Smell defect. CG – Violation of a coding guideline. Provide the guideline number. FD – Functional defect. Code will not produce the expected result. MD – Miscellaneous defect, for all other defects.

Severity: BR - Blocker, must be fixed asap. MJ – Major, of high importance but not a Blocker LOW – Low. 
