# *Wikidata* Explorer

*Wikidata* Explorer is a simple interface to the world of
structured data and knowledge graphs.

## Introduction

*Wikidata* Explorer allows the user to see the relationships
between information about the world. The application is rather
simple, You can select a concept, property, etc. to start with
(or use the default concept). From there, basic information is
shown along with important properties. You can also expand the
properties list to show all of them or search for a specific
one. If a property interests you, then you can pull its card
out to the side and explore additional information.

This application is of interest to anyone who is interested in
structured data, computational intelligence, or just finds the
relationships between things fascinating. This application
provides an overview of *Wikidata* that allows a better
understanding of the relationships which are the core of
*Wikidata*. It's helpful for those interested in using the
*Wikidata* API as it allows you to take a glimpse into the
structure which makes it much easier to determine how to best
utilize the data.

I have been fascinated with *Wikidata* for a long time. I
thought it was incredibly interesting how concepts,
properties, lexemes, and other data-types are related to
each-other. I keep a list of ideas for coding projects on my
phone so that I always have once when I need it. Many of the
ideas don't work in Java or don't fit the requirements of this
project (I mostly make cli tools). It just happened that
this one (item number 10 on my list) does work which is why I
decided to work on it this time.

## *Wikidata* Basics
*Wikidata* stores a collection of structured data, that means that every piece
of data is linked to other pieces of data. The full format is rather complex, so
I will just cover the basics. Any "concept" has a concept ID starting with a
**Q**. For example, **Q2** is **Earth**, and **Q42** is **Douglas Adams** (the
author) every concept has a label which may optionally be in multiple languages.
**Q2** has the label "Earth" in English, "Terre" in french, "Terra" in Italian,
etc. Each language also has a description, and a list of alternative names such
as "Pale Blue Dot" in English and "pianeta azzurro" in Italian. There is then a
list of statements. Each statement consists of a property (which has an ID
starting with a **P**), a list of values for that property, an optional list of
qualifiers for each value, and a list of references (each reference itself
having properties and values) for each value. Every property also has its own
label, description, etc. describing it and often an associated concept.

It gets a lot more complex than this. There are many other datatypes, properties
and relationships which can be encoded with this system. I won't get into all of
it but one of the nice things about this system is that it allows you to take
human language statements and turn them into values that you can look up. For
example, the English statement:

* "Mount Everest is the highest mountain in the
world"

can be transformed into the statement:

* "**Earth**
(<sub><sup>**Q2**</sup></sub>)
(<sub><sup>item</sup></sub>) → **highest point**
(<sub><sup>**P610**</sup></sub>) (<sub><sup>property</sup></sub>) →
**Mount Everest** (<sub><sup>**Q513**</sup></sub>)
(<sub><sup>value</sup></sub>)"

or something even simpler for computers to
understand like

* "**Q2** → **P610** → **Q513**".

While Wikidata itself may not
be used for all of these applications, it is this kind of data and statement
system that powers common assistants like Google Assistant, and Siri. That's why
I was interested in making this program, many people may wish to learn about
Wikidata's structured data system to see if they can incorporate it into their
own projects and systems so this program is a good way to get an overview to see
how data is organized.

## User stories
* As a user, I want to be able to query information about an entity.
* As a user, I want to view statements and save those which I am interested in.
* As a user, I want to add multiple downstream entities to my view.
* As a user, I want to search for an entity by name.
* As a user, I want to ask simple questions and validate statements.
