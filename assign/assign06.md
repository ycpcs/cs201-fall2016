---
layout: jquery
title: "Assignment 6: Web Crawler"
---

*Preliminary version, not official yet*

**Due**: Tuesday, December 13th by 11:59 PM

# Getting started

Download [CS201\_Assign06.zip](CS201_Assign06.zip) and import it into your Eclipse workspace (**File&rarr;Import&rarr;General&rarr;Existing projects into workspace&rarr;Archive file**.)

You should see a project called **CS201\_Assign06** in the Package Explorer. Your will be implementing the methods in the **URL** class and the **WebCrawler** class.

# Your task

## URLs

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
* `equals`: compares two URL objects to check whether they are equal to each other
* `toString`: returns the textual representation of a URL

### Finding the canonical form of a URL

A URL is in *canonical form* if its path does not contain any occurrences of "." and "..".

*TODO: example of a directory hierarchy and how a path specifies a way to navigate to a resource.*

### Getting a referenced URL

Uh-huh

## Web crawler

Coming soon.

# Hints and specifications

Yeah.

# Grading criteria

Yeah.

# Submitting

Save the project (**CS201\_Assign06**) to a zip file by right-clicking it and choosing

> **Export...&rarr;Archive File**

Upload the saved zip file to the Marmoset server as **assign06**. The server URL is

> <https://cs.ycp.edu/marmoset/>

<script>
$(document).ready(function() {
	$(".up").attr('title', 'Protocol').tooltipster();
	$(".uh").attr('title', 'Host').tooltipster();
	$(".upa").attr('title', 'Path').tooltipster();
});
</script>
