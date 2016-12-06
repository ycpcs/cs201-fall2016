---
layout: jquery
title: "Assignment 6: Web Crawler"
---

**Due dates**:

* Milestone 1 is due Tuesday, Dec 6th by 11:59 PM
* Milestone 2 is due Tuesday, Dec 13th by 11:59 PM

*Update 12/2*:

* `http:` and `https:` URLs must have a host part
* link to more complete **URLTest.java**

*Update 12/5*:

* Post skeleton for Milestone 2 and requirements for the **LinkExtractor** class
* Add a hint about how to split a URL path into components

*Update 12/6*:

* Describe requirements for the **Crawler** class

# Getting started

## Milestone 1

Download [CS201\_Assign06\_MS1.zip](CS201_Assign06_MS1.zip) and import it into your Eclipse workspace (**File &rarr; Import &rarr; General &rarr; Existing projects into workspace &rarr; Archive file**.)

You should see a project called **CS201\_Assign06\_MS1** in the Package Explorer. Your will be implementing the methods in the **URL**.

*Update 12/2*: The original version of **URLTest.java** did not have any tests for the `getDirectoryPart` method.  Here is a fixed version:

> [URLTest.java](URLTest.java)

## Milestone 2

Download [CS201\_Assign06\_MS2.zip](CS201_Assign06_MS2.zip) and import it into your Eclipse workspace (**File &rarr; Import &rarr; General &rarr; Existing projects into workspace &rarr; Archive file**.)

You should see a project called **CS201\_Assign06\_MS2** in the Package Explorer.  You will need to copy the **URL** class from your Milestone 1 project into the Milestone 2 project.  Your will be implementing the methods in the **LinkExtractor** and **Crawler** class.


# Your task

## Milestone 1: URLs

Your first task is to implement the methods in the **URL** class so that the tests in **URLTest** pass.

<div class="callout">
<b>Very important</b>: Do <em>not</em> use the <code>java.net.URL</code> class, or any other classes in the <code>java.net</code> package, in your implementation of the <b>URL</b> class.
</div>

A URL is a string of text specifying the location of a web resource.  A URL consists of a *protocol* (optional), followed by a *host* (optional), followed by a *path* (required).

A protocol is a sequence of characters ending in a colon (":").

A host starts with "//" and then consists of a sequence of non-slash characters.  If the URL's protocol is either `http:` or `https:`, then the host part of the URL is *not* optional.

A path is a sequence of components separated by slashes.  Each component is a sequence of non-slash characters.  As a special case, a single slash character ("/") is also considered a valid path.  Also note that if the URL specifies a host, the path *must* start with a slash.

Here are some example URLs with the parts (<span class="upcol">protocol</span>, <span class="uhcol">host</span>, and <span class="upacol">path</span>) identified and color-coded:

<pre>
<span class="up">http:</span><span class="uh">//ycpcs.github.io</span><span class="upa">/cs201-fall2016/assign/assign06.html</span>

<span class="up">https:</span><span class="uh">//ycpcs.github.io</span><span class="upa">/cs201-fall2016/assign/assign06.html</span>

<span class="up">https:</span><span class="uh">//ycpcs.github.io</span><span class="upa">/cs201-fall2016/</span>

<span class="up">https:</span><span class="uh">//ycpcs.github.io</span><span class="upa">/</span>

<span class="uh">//ycpcs.github.io</span><span class="upa">/cs201-fall2016/assign/assign06.html</span>

<span class="upa">foo/bar/baz.html</span>

<span class="upa">foo/bar/baz/../thud/../../blat.html</span>

<span class="up">https:</span><span class="uh">//ycpcs.github.io</span><span class="upa">/cs201-fall2016/assign/../practice/index.html</span>
</pre>

A URL is *absolute* if its path begins with a slash.

A URL is a *directory* if its path ends with a slash.

A URL is *canonical* if its path does not contain any "." or ".." components.

A URL *object* is an instance of the **URL** class, and represents the information extracted from the textual form of a URL.  You will need to implement the following methods:

* Constructor from string: initializes a URL object from a string containing the textual form of a URL
* Copy constructor: initializes a URL object from an existing URL object, making the new URL object identical to the original URL object
* `isAbsolute`: returns true if the URL is absolute, false otherwise
* `isDirectory`: returns true if the URL is a directory, false otherwise
* `getProtocol`: returns the URL's protocol, or the empty string if the URL does not specify a protocol
* `getHost`: returns the URL's host, or the empty string if the URL does not specify a host
* `getPath`: returns the URL's path
* `getDirectoryPart`: returns the directory part of the URL's path, which is everything up to the last occurrence of "/"; as a special case, if the path has no occurrences of "/", then the directory part is the empty string
* `isCanonical`: returns true if the URL is canonical, false otherwise
* `makeCanonical`: returns a URL object which is the canonical form of the original URL (see below)
* `getReferencedURL`: returns a URL referenced by a link occurring in a web document in absolute canonical form (see below)
* `toString`: returns the textual representation of a URL

Note that the `equals`, `hashCode`, and `compareTo` methods are already defined: you will not need to modify them.  (All of them call `toString`, so they will not work correctly unless `toString` works correctly.)

Also note that **URL** is an *immutable class*.  This means that none of the methods supported by **URL** objects modify the object's data.  In other words, **URL** objects are "read only" once they are created.

### Finding the canonical form of a URL

A URL is in *canonical form* if its path does not contain any occurrences of "." and "..".  Many URLs which are not in canonical form can be converted to canonical form.

To understand how to convert a URL to canonical form, let's consider what the path of a URL means.  The path is a description of how to get from a starting point to a destination in a hierarchical directory structure.  Consider the following hierarchy:

    /
        reptiles/
            lizards/
                monitorLizard.html
                komodoDragon.html
                gilaMonster.html
        mammals/
            marsupials/
                koala.html
                potoroo.html
                wombat.html
            monotremes/
                platypus.html
                echidna.html
        dinosaurs/
            therizinosaurs/
                therizinosaurus.html
                nothronychus.html
                beipaosaurus.html

We can specify a path which describes how to get from the root (`/`) of the hierarchy to any resource in the hierarchy.  For example, the path

    /mammals/marsupials/potoroo.html

means "start in the root directory, then go into the mammals directory, then go into the marsupials directory, then find the resource called potoroo.html".  This is an example of an *absolute* path, because it starts in the root of the hierarchy.  Paths can also be relative, which means that the location of a resource is specified in relation to another resource whose exact location in the hierarchy is known.  For example, the path

    monotremes/echidna.html

is relative.  If the starting point is the directory

    /mammals/

then the exact resource being named is the concatenation of the starting directory and the relative path, which in this case is

    /mammals/monotremes/echidna.html

This brings us to the meaning of the "." and ".." path components:

* "." means "stay in the current directory"
* ".." means "go back to the parent directory"

Consider the relative path

    ../../reptiles/lizards/./monitorLizard.html

If the starting point directory is

    /dinosaurs/therizinosaurs/

then the complete path is

    /dinosaurs/therizinosaurs/../../reptiles/lizards/./monitorLizard.html

What does this mean?  Reading the path from left to right, it means

* start in the root directory
* then go into the dinosaurs directory
* then go into the theriznosaurs directory
* then go back to the parent directory
* then go back to the parent directory
* then go into the reptiles directory
* then go into the lizards directory
* then stay in the same directory
* then find the resource monitorLizard.html

This does indeed allow us to find the resource, but the ".." and "." components are redundant.  A URL in *canonical form*, because it describes the simplest path to find a resource, is a better representation of the location of the resource.  In addition, for any URL that can be put in canonical form, the canonical form is unique.

Here is how we can convert a URL not in canonical form to canonical form:

1. convert the URL's path into a sequence of components
2. create a stack of string values
3. for each component, in order:
    * if the component is ".", ignore it
    * if the component is "..", pop the stack
    * otherwise, push the component onto the stack

If the process completes successfully, the components in the stack (from bottom to top) are the components of the canonical form of the path.  (One reason that conversion to canonical form may fail is if a ".." component is seen, but the stack is empty.)

In the example above, the canonical form of the path is

    /reptiles/lizards/monitorLizard.html

### Getting a referenced URL

So, if having a URL in canonical form is a good thing, why are the "." and ".." components used at all?

The reason is that when one web page links to another resource, it may be convenient to specify the linked resource's location relative to its own location.  For example, if a web page with the path

    /dinosaurs/therizinosaurs/nothronychus.html

contains a link

    ../sauropods/argentinosaurus.html

then the effective path of the linked resource is

    /dinosaurs/therizinosaurs/../sauropods/argentinosaurus.html

In other words, we take the directory part of the document containing the link, and append to it the relative link.  The idea is that a ".." path component allows a link to a resource "below" or "beside" the web page containing the link.

Because canonical form is always the best way to specify the location, of a resource, referenced URLs should be converted into canonical form.  In the example above, the canonical form of the linked resource's path would be

    /dinosaurs/sauropods/argentinosaurus.html

The `getReferencedURL` method in the **URL** class should do the following:

1. If the referenced URL's path is absolute, convert it to canonical form and return the canonical form
2. If the referenced URL's path is not absolute, then
    1. Find the referenced URL's effective path by appending it onto the directory part of the "base" URL (which is the **URL** object the method is called on)
    2. Return the canonical form of the URL whose protocol and host are the same as the base URL, but whose path is the effective path of the referenced URL

## Milestone 2: Web crawler

In the second milestone, you will complete the implementation of two classes, **LinkExtractor** and **Crawler**.

### `LinkExtractor` class

The **LinkExtractor** class is used to scan the text of a web page for links to other resources (including other web pages.)

For the purposes of this assignment, you can assume that a link is text between the delimiters `href="` and `"`.  You may also assume that any line of text in a web page will contain at most one link.

As an example, consider the following web page:

    <html>
      <body>
        <a href="coolStuff.html">Some cool stuff</a>
        
        <a href="info/resources.html">Resources</a>
      </body>
    </html>

This web page has two links:

    coolStuff.html
    info/resources.html

To implement the **LinkExtractor** class, you should do the following:

* Add a field to keep track of extracted links (hint: use a `Set<String>`)
* Modify the constructor to initialize this field (hint: create a set object)
* Modify the `processLine` method so that it looks for a link in the specified line of text, and if one is found, adds it to the set
* Modify the `getExtractedLinks` method to return the set of extracted links

You can use the **LinkExtractorTest** class to test your **LinkExtractor** implementation.  Make sure all of the tests pass.

### `Crawler` class

The **Crawler** class implements a "web crawl":

* It starts at a page named by a starting URL
* The starting URL is added to a queue
* While the queue is not empty:
    1. Extract the URL
    2. Ignore the URL if it names a page that has already been visited; otherwise,
    3. The page named by the URL is loaded, and the links are extracted
    4. The links are convered to absolute canonical form (using the `getReferencedURL` method)
    5. Links which refer to valid pages are added to a queue 

This algorithm is implemented in the `crawl` method, and is a form of the *breadth-first search* algorithm.

Your task is to complete the following methods:

* `alreadyVisited`
* `markVisited`
* `recordBrokenLink`
* `getVisited`
* `getBrokenLinks`

Each method is described by a comment.

You will need to read the code in the `crawl` method carefully to understand the role of each of these methods in the crawling algorithm.

The **FileCrawlerTest** class has unit tests for the **Crawler** class: make sure these pass.  You can also try the **HttpCrawlerTest** class, which runs the same tests as **FileCrawlerTest**, but loads web pages from the course website rather than from files.

# Hints and specifications

For the `makeCanonical` method in `URL`, you can split a path into components as follows.  Assume that the `path` variable is the path part of a URL.  The following code will break the path into an array of components:

{% highlight java %}
String[] components = path.split("/");
{% endhighlight %}

Note that if the path starts with "/" there will be an empty first component, and if the path ends with "/" there will be an empty last component.

For `LinkExtractor` the following methods in the String class may be useful.  For example, if `s` is a string:

* `s.indexOf(c)`, where `c` is a character gives you the index of the first occurrence of `c` in `s`, or -1 if there are no occurrences
* `s.indexOf(sub)`, where `sub` is a string, gives you the index of the first occurrence of `sub` in `s`, or -1 if there are no occurrences
* `s.substring(start, end)` gives you the substring of `s` from index `start` (inclusive) to index `end` (exclusive)

# Grading criteria

Milestone 1:

* constructor from string: 10%
* copy constructor: 10%
* `isAbsolute`: 6%
* `isDirectory`: 6%
* `getProtocol`: 6%
* `getHost`: 6%
* `getPath`: 6%
* `getDirectoryPart`: 6%
* `isCanonical`: 6%
* `makeCanonical`: 20%
* `getReferencedURL`: 10%
* `toString`: 8%

Points may be deducted for poor coding style such as non-private fields, inconsistent indentation, etc.

Milestone 2:

* **LinkExtractor** class:
    * fields: 7%
    * constructor: 8% 
    * `processLine` method: 30%
    * `getExtractedLinks`: method 5%
* **Crawler** class:
    * `alreadyVisited`: 10%
    * `markVisited`: 10%
    * `recordBrokenLink`: 10%
    * `getVisited`: 10%
    * `getBrokenLinks`: 10%

# Submitting

Save the project (**CS201\_Assign06\_MS1** or **CS201\_Assign06\_MS2**) to a zip file by right-clicking it and choosing

> **Export...&rarr;Archive File**

Upload the saved zip file to the Marmoset server as **assign06\_ms1** or **assign06\_ms2** (for Milestones 1 and 2, respectively). The server URL is

> <https://cs.ycp.edu/marmoset/>

<script>
$(document).ready(function() {
	$(".up").attr('title', 'Protocol').tooltipster();
	$(".uh").attr('title', 'Host').tooltipster();
	$(".upa").attr('title', 'Path').tooltipster();
});
</script>

<!-- vim:set wrap: ­-->
<!-- vim:set linebreak: -->
<!-- vim:set nolist: -->
