# noSQL, Document stores and mongoDB

## Overview
Relational databases were first described in 1970 and quickly matured, becoming nearly synonymous with databases themselves. They offer a powerful, stable framework to store and analyse large amounts of well-structured, predictable data. 

Only when the Internet gained traction in the late 1990s/early 2000s was this status challenged: The huge amounts of varied data created for and by the Web were difficult to handle for traditional relational databases. Many types of new, mostly non-relational<sup>1</sup> databases were created, collectively called "noSQL" (not only SQL). 

Although relational databases remain the most common type today, noSQL databases are used for a wide variety of purposes. The most commonly used of these is mongoDB, a document store (or document-oriented database) storing documents as JSON-like objects.

## Key differences

Where RDBMS enforce a strict structure, noSQL DBMS omit some or most of these requirements. By not storing data in tables with predefined attributes and data types, noSQL DMBS gain the ability to store any kind of data, but lose the guaranteed integrity that forms the basis of RDBMS. The designers and programmers have to decide which of these restrictions must be enforced and which can be safely ignored: Unless specifically instructed not to, most noSQL DBMS will allow inconsistent or redundant data!

Other than speed, a significant advantage of noSQL databases is the ease of modification. Making changes to the structure of an existing relational database can become exceedingly difficult over time, whereas the looser structure of noSQL databases makes it easier to apply larger structural changes.

## Types
 noSQL DBMS exist for a variety of purposes, some of these are:
 - Key-Value stores store data exclusively as key-value pairs.
 - Document stores are used to save documents in the form of JSON, BSON or XML documents.
 - Graph databases focus on the relationships between individual elements (nodes).
- Column-oriented databases store data as columns instead of full tables and excel at analytics.

In reality the distinction is not always that simple, many more categories exist and many DBMS offer multiple models.

## Example
To illustrate the use cases, let's take a look at a simple example. Here is a JSON object containing data about a book which we want to store:

```
{
    "title": "Spass mit Datenbanken",
    "author": "Stefan Stanzel",
    "publisher": "Springer Science + Business Media",
    "isbn": "978-3-16-148410-0"
}
```

To store this book in a relational database we would have to think about how these attributes relate to each other: We'd end up with 3 Tables for Books, Authors and Publishers, plus a fourth one to resolve the many-to-many relationship between Book and Author. This can be a perfectly valid approach for, for example, a publisher or a library. These companies would store large amounts of books in their database, all represented by a very similar or identical structure. 

If, however, you simply want to store this JSON object together with many other objects with wildly different structures (or unknown structures!), document stores offer a simpler approach. MongoDB saves data as BSON structures (binary JSON), offering a direct way of storing the ubiquitous JSON objects. Here's how that would look like in the database:
``` 
{
    _id: ObjectId("610c4d3b4d3b4b8bc7f718b27af8"),
    title: 'Spass mit Datenbanken',
    author: 'Stefan Stanzel',
    publisher: 'Springer Science + Business Media',
    isbn: '978-3-16-148410-0'
}
```

## MongoDB

MongoDB was initially released in 2009 and has since become the most widely used noSQL database. Its main features are speed, flexibility and easy sharding.

As discussed above, by omitting the static, well-defined structure of a relational database mongoDB gains a significant speed advantage and can easily store wildly differing types of data. Where required, a schema can be build to ensure integrity and structure of some or all kinds of data. Objects can also be structured to use relationships.

Since data is (compared to a RDBMS) less connected, it is very easy for mongoDB to horizontally partition its data. This allows for easy separation of data between multiple servers (called sharding), improving overall performance.

The most immediate downsides are the lack of built-in consistency and redundancy checking (no normal forms) and the inability to use the well-known SQL language.

## Helpful Links
To quickly get an idea on how using mongoDB looks like you can try the  [webshell in the tutorial pages](https://docs.mongodb.com/manual/tutorial/getting-started/)
For a more guided approach, the [mongoDB University](https://university.mongodb.com/) offers a large amount of online courses to get you started, beginning with the [Basic Course](https://university.mongodb.com/courses/M001/about).

<br><br>
<sup>1</sup>: Originally "noSQL" implied a non-relational database, but since then it has come to mean "anything not using SQL", including some relational databases using SQL-like languages.