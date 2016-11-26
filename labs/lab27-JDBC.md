---
layout: default
title: "Lab 27: JDBC"
---

Getting Started
===============
We'll continue our work on SQL databases this week by again "borrowing" from the CS320 SW Engineering labs.

Download [CS320\_Lab05.zip](CS320_Lab05.zip). Import it into your Eclipse workspace (**File&rarr;Import...&rarr;General&rarr;Existing projects into workspace&rarr;Archive File**). You will see a project called **CS320\_Lab05** in the Package Explorer.  (You will also need to have the **CS320_Derby** project in your workspace, which should already be there from [Lab 26](lab26-SQLDB.html).)

The lab contains a database called **test.db** which is the books database described in [Lecture 26](../lectures/lecture26.html).

You may work individually or with a small group.

Task
====

In the lab skeleton you will find a program called **TitleQuery** which demonstrates basic JDBC tasks such as loading a driver, connecting to a database, creating and executing a prepared statement, and iterating through results returned from a query.

Using **TitleQuery** as a model, write your own programs to do the following:

**(1)** Find all books written by the author whose last name is specified. Return the books in the same form as the **TitleQuery** program.

**(2)** Given the full (first and last) name of an author, a title, and an ISBN, insert the book into the database. The program should add a new tuple to the authors table if the author doesn't already exist. If the author does exist already, the program should use that author's **author\_id**.

Use the SQL **insert** statement to insert the new tuple(s).
