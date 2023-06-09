# TypeDB Onboarding Project

## Domain: Math Genealogy Project

## Dataset

Real data from [Math Genealogy Project API](https://mathgenealogy.org:8000/api/v2/MGP/login).

## Questions to explore

1. Which mathematicians who have graduated from the University of Illinois at Urbana-Champaign specialize in Differential Geometry?
2. Which schools offer a program in Commutative Ring and Algebras?
3. Show the unordered pairs of all mathematicians who have at-least one student in common.
4. Which schools offer the most variety of programs for specializations?
5. Which fields have the most mathematicians working on them?
## How to load the data

1. Run TypeDB Version 2.16.1 at localhost:1729
2. Run `bazel run //:json-test.java`

## How to run the tests

1. Run TypeDB Version 2.16.1 at localhost:1729
2. Run `bazel test //:DataLoaderTest`

## Answers discovered!
1. Which mathematicians who have graduated from the University of Illinois at Urbana-Champaign specialize in Differential Geometry?
### Query
```
match
    $m isa mathematician, has name $n;
    $s isa speciality, has name "53—Differential geometry";
    $sc isa school, has name "University of Illinois at Urbana-Champaign";
    (school: $sc, student: $m) isa studentship;
    (master: $m, speciality:$s) isa mastery;
get $n;
```
### Answer
```
{ $n "Anton Mikhailovich Petrunin" isa name; }
```

2. Which schools offer a program in Commutative Ring and Algebras?
## Query
```
match
$sc isa school, has name $n;
$s isa subject;
$spe isa speciality, has name "13—Commutative rings and algebras";

(school: $sc, subject:$s, speciality:$spe) isa offer;
get $n;
```

### Answer
```
{ $n "University of Tennessee - Knoxville" isa name; 
```

3. Show the unordered pairs of all mathematicians who have at-least one student in common.

### Query
```
#the underlying rule
define 
rule advisors-with-common-students-are-co-teach:
    when
    {
        $m isa mathematician;
        $a1 isa mathematician, has id $id1;
        $a2 isa mathematician, has id $id2;
        $s1 isa school;
        $s2 isa school;
        not {$a1 is $a2;};
        $id1<$id2;
        (advisor: $a1, advisee: $m, school: $s1) isa research;
        (advisor: $a2, advisee: $m, school: $s2) isa research;
    }
    then
    {
        (advisor1: $a1, advisor2: $a2) isa co-teach;
    };
    
#the query
match
   $s1 isa mathematician, has name $n1;
   $s2 isa mathematician, has name $n2;
  $r (advisor1: $s1, advisor2: $s2) isa co-teach; 
```

### Answer
```
{
    $r iid 0x847080017ffffffffffffffe (advisor2: iid 0x826e80028000000000000d8e, advisor1: iid 0x826e80028000000000000d8f) isa co-teach;
    $n1 "Raymond Yen-Tin Wong" isa name;
    $n2 "James Edward West" isa name;
    $s1 iid 0x826e80028000000000000d8f isa mathematician;
    $s2 iid 0x826e80028000000000000d8e isa mathematician;
}
{
    $r iid 0x847080017fffffffffffffff (advisor2: iid 0x826e80028000000000000d88, advisor1: iid 0x826e80028000000000000d8a) isa co-teach;
    $n1 "Beverly Lorraine Brechner" isa name;
    $n2 "Deborah Rebhuhn" isa name;
    $s1 iid 0x826e80028000000000000d8a isa mathematician;
    $s2 iid 0x826e80028000000000000d88 isa mathematician;
}
```

4. Which schools offer the most variety of programs for specializations?

### Query
```
match
$sc isa school,
    has name $n;
$spe isa speciality,
    has name $n2;
$s isa subject;
(school: $sc, subject: $s, speciality: $spe) isa offer;
get $n, $n2;
group $n; count
```
### Answer
```
"Louisiana State University" isa name => 1
"University of Wisconsin-Madi" isa name => 1
"University of Houston" isa name => 1
"University of Tennessee - Knoxville" isa name => 7
"University of Nebraska-Lincoln" isa name => 1
"University of Illinois at Urbana-Champaign" isa name => 3
"Iowa State University" isa name => 2
"University of California, Berkeley" isa name => 1
"University of Texas at Austin" isa name => 1
"University of Wisconsin-Madison" isa name => 2
```

5. Which fields have the most mathematicians working on them?

### Query
```
match 
    $s isa speciality, has name $n;
    $m isa mathematician;
    (master: $m, speciality:$s) isa mastery;
get $m, $n; group $n; count; 
```

```
"Unknown" isa name => 127
"65—Numerical analysis" isa name => 1
"08—General algebraic systems" isa name => 1
"53—Differential geometry" isa name => 1
"74—Mechanics of deformable solids" isa name => 2
"05—Combinatorics" isa name => 1
"97—Mathematics education" isa name => 1
"57—Manifolds and cell complexes" isa name => 3
"13—Commutative rings and algebras" isa name => 1
"20—Group theory and generalizations" isa name => 1
```
