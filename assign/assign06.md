---
layout: jquery
title: "Assignment 6: Web Crawler"
---

*Preliminary version, not official yet*

**Due dates**:

* Milestone 1 is due Tuesday, Dec 6th by 11:59 PM
* Milestone 2 is due Tuesday, Dec 13th by 11:59 PM

# Getting started

Download [CS201\_Assign06.zip](CS201_Assign06.zip) and import it into your Eclipse workspace (**File&rarr;Import&rarr;General&rarr;Existing projects into workspace&rarr;Archive file**.)

You should see a project called **CS201\_Assign06** in the Package Explorer. Your will be implementing the methods in the **URL** class and the **WebCrawler** class.

# Your task

## Milestone 1: URLs

Your first task is to implement the methods in the **URL** class so that the tests in **URLTest** pass.

A URL is a string of text specifying the location of a web resource.  A URL consists of a *protocol* (optional), followed by a *host* (optional), followed by a *path* (required).

A protocol is a sequence of characters ending in a colon (":").

A host starts with "//" and then consists of a sequence of non-slash characters.

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

Note that the `equals` and `compareTo` methods are already defined: you will not need to modify them.

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

If the process completes successfully, the components in the stack (from bottom to top) are the components of the canonical form of the path.

In the example above, the canonical form of the path is

    /reptiles/lizards/monitorLizard.html

One reason that conversion to canonical form may fail is if a ".." component is seen, but the stack is empty.

### Getting a referenced URL

So, if having a URL in canonical form is a good thing, why are the "." and ".." components used at all?

Explain...

## Milestone 2: Web crawler

Coming soon.

# Hints and specifications

Yeah.

# Grading criteria

Milestone 1:

* stuff 1
* stuff 2

Milestone 2:

* coming soon

# Submitting

Save the project (**CS201\_Assign06**) to a zip file by right-clicking it and choosing

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
