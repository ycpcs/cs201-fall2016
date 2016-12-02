package edu.ycp.cs201.webcrawler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class URLTest {
	private static final String ASSIGNMENT_DESC_URL = "http://ycpcs.github.io/cs201-fall2016/assign/assign06.html";
	private static final String RELATIVE_LINK_URL = "foo/bar/baz.html";
	private static final String RELATIVE_NON_CANONICAL_LINK_URL = "../../fizz/buzz.html";
	private static final String COURSE_WEBSITE_DIR_URL = "http://ycpcs.github.io/cs201-fall2016/";
	private static final String SECURE_ASSIGNMENT_DESC_URL = "https://ycpcs.github.io/cs201-fall2016/assign/assign06.html";
	private static final String SECURE_COURSE_WEBSITE_DIR_URL = "https://ycpcs.github.io/cs201-fall2016/";
	private static final String ASSIGNMENT_DESC_IMPLICIT_PROTO_URL = "//ycpcs.github.io/cs201-fall2016/assign/assign06.html";
	private static final String RELATIVE_NON_CANONICAL_LINK2_URL = "foo/bar/baz/../thud/../../blat.html";
	private static final String ASSIGNMENT_DESC_NON_CANONICAL_URL = "http://ycpcs.github.io/cs201-fall2016/labs/images/../../assign/img/../assign06.html";
	private static final String ABSOLUTE_LINK = "/cs201-fall2016/practice/index.html";
	
	private URL assignmentDesc;
	private URL relativeLink;
	private URL relativeNonCanonicalLink;
	private URL courseWebsiteDir;
	private URL secureAssignmentDesc;
	private URL secureCourseWebsiteDir;
	private URL assignmentDescImplicitProto;
	private URL relativeNonCanonicalLink2;
	private URL assignmentDescNonCanonical;
	private URL absoluteLink;
	
	@Before
	public void setUp() {
		// http URL naming a file
		assignmentDesc = new URL(ASSIGNMENT_DESC_URL);
		
		// relative URL naming a file
		relativeLink = new URL(RELATIVE_LINK_URL);
		
		// relative non-canonical URL naming a file, which can't be made
		// canonical directly because it accesses path components
		// "below" the location the URL is relative to
		relativeNonCanonicalLink = new URL(RELATIVE_NON_CANONICAL_LINK_URL);
		
		// http URL naming a directory (the root directory of the course website)
		courseWebsiteDir = new URL(COURSE_WEBSITE_DIR_URL);
		
		// https URL naming a file
		secureAssignmentDesc = new URL(SECURE_ASSIGNMENT_DESC_URL);
		
		// https URL naming a directory
		secureCourseWebsiteDir = new URL(SECURE_COURSE_WEBSITE_DIR_URL);
		
		// remote (http or https) URL with an "implicit" protocol
		assignmentDescImplicitProto = new URL(ASSIGNMENT_DESC_IMPLICIT_PROTO_URL);
		
		// relative non-canonical URL naming a file which can be made canonical
		relativeNonCanonicalLink2 = new URL(RELATIVE_NON_CANONICAL_LINK2_URL);
		
		// absolute non-canonical http URL naming a file
		assignmentDescNonCanonical = new URL(ASSIGNMENT_DESC_NON_CANONICAL_URL);
		
		// absolute link that doesn't specify a protocol or host
		absoluteLink = new URL(ABSOLUTE_LINK);
	}
	
	@Test
	public void testGetProtocol() throws Exception {
		assertEquals("http:", assignmentDesc.getProtocol());
		assertEquals("", relativeLink.getProtocol());
		assertEquals("", relativeNonCanonicalLink.getProtocol());
		assertEquals("http:", courseWebsiteDir.getProtocol());
		assertEquals("https:", secureAssignmentDesc.getProtocol());
		assertEquals("https:", secureCourseWebsiteDir.getProtocol());
		assertEquals("", assignmentDescImplicitProto.getProtocol());
		assertEquals("", relativeNonCanonicalLink2.getProtocol());
		assertEquals("http:", assignmentDescNonCanonical.getProtocol());
		assertEquals("", absoluteLink.getProtocol());
	}
	
	@Test
	public void testGetHost() throws Exception {
		assertEquals("ycpcs.github.io", assignmentDesc.getHost());
		assertEquals("", relativeLink.getHost());
		assertEquals("", relativeNonCanonicalLink.getProtocol());
		assertEquals("ycpcs.github.io", courseWebsiteDir.getHost());
		assertEquals("ycpcs.github.io", secureAssignmentDesc.getHost());
		assertEquals("ycpcs.github.io", secureCourseWebsiteDir.getHost());
		assertEquals("ycpcs.github.io", assignmentDescImplicitProto.getHost());
		assertEquals("", relativeNonCanonicalLink2.getHost());
		assertEquals("ycpcs.github.io", assignmentDescNonCanonical.getHost());
		assertEquals("", absoluteLink.getHost());
	}
	
	@Test
	public void testIsAbsolute() throws Exception {
		assertTrue(assignmentDesc.isAbsolute());
		assertFalse(relativeLink.isAbsolute());
		assertFalse(relativeNonCanonicalLink.isAbsolute());
		assertTrue(courseWebsiteDir.isAbsolute());
		assertTrue(secureAssignmentDesc.isAbsolute());
		assertTrue(secureCourseWebsiteDir.isAbsolute());
		assertTrue(assignmentDescImplicitProto.isAbsolute());
		assertFalse(relativeNonCanonicalLink2.isAbsolute());
		assertTrue(assignmentDescNonCanonical.isAbsolute());
		assertTrue(absoluteLink.isAbsolute());
	}
	
	@Test
	public void testIsDirectory() throws Exception {
		assertFalse(assignmentDesc.isDirectory());
		assertFalse(relativeLink.isDirectory());
		assertFalse(relativeNonCanonicalLink.isDirectory());
		assertTrue(courseWebsiteDir.isDirectory());
		assertFalse(secureAssignmentDesc.isDirectory());
		assertTrue(secureCourseWebsiteDir.isDirectory());
		assertFalse(assignmentDescImplicitProto.isDirectory());
		assertFalse(relativeNonCanonicalLink2.isDirectory());
		assertFalse(assignmentDescNonCanonical.isDirectory());
		assertFalse(absoluteLink.isDirectory());
	}
	
	@Test
	public void testIsCanonical() throws Exception {
		assertTrue(assignmentDesc.isCanonical());
		assertTrue(relativeLink.isCanonical());
		assertFalse(relativeNonCanonicalLink.isCanonical());
		assertTrue(courseWebsiteDir.isCanonical());
		assertTrue(secureAssignmentDesc.isCanonical());
		assertTrue(secureCourseWebsiteDir.isCanonical());
		assertTrue(assignmentDescImplicitProto.isCanonical());
		assertFalse(relativeNonCanonicalLink2.isCanonical());
		assertFalse(assignmentDescNonCanonical.isCanonical());
		assertTrue(absoluteLink.isCanonical());
	}
	
	@Test
	public void testGetPath() throws Exception {
		assertEquals("/cs201-fall2016/assign/assign06.html", assignmentDesc.getPath());
		assertEquals(RELATIVE_LINK_URL, relativeLink.getPath());
		assertEquals(RELATIVE_NON_CANONICAL_LINK_URL, relativeNonCanonicalLink.getPath());
		assertEquals("/cs201-fall2016/", courseWebsiteDir.getPath());
		assertEquals("/cs201-fall2016/assign/assign06.html", secureAssignmentDesc.getPath());
		assertEquals("/cs201-fall2016/", secureCourseWebsiteDir.getPath());
		assertEquals("/cs201-fall2016/assign/assign06.html", assignmentDescImplicitProto.getPath());
		assertEquals(RELATIVE_NON_CANONICAL_LINK2_URL, relativeNonCanonicalLink2.getPath());
		assertEquals("/cs201-fall2016/labs/images/../../assign/img/../assign06.html",
				assignmentDescNonCanonical.getPath());
		assertEquals(ABSOLUTE_LINK, absoluteLink.getPath());
	}
	
	@Test
	public void testGetDirectoryPart() throws Exception {
		assertEquals("/cs201-fall2016/assign/", assignmentDesc.getDirectoryPart());
		assertEquals("foo/bar/", relativeLink.getDirectoryPart());
		assertEquals("../../fizz/", relativeNonCanonicalLink.getDirectoryPart());
		assertEquals("/cs201-fall2016/", courseWebsiteDir.getDirectoryPart());
		assertEquals("/cs201-fall2016/assign/", secureAssignmentDesc.getDirectoryPart());
		assertEquals("/cs201-fall2016/", secureCourseWebsiteDir.getDirectoryPart());
		assertEquals("/cs201-fall2016/assign/", assignmentDescImplicitProto.getDirectoryPart());
		assertEquals("foo/bar/baz/../thud/../../", relativeNonCanonicalLink2.getDirectoryPart());
		assertEquals(
				"/cs201-fall2016/labs/images/../../assign/img/../",
				assignmentDescNonCanonical.getDirectoryPart());
		assertEquals("/cs201-fall2016/practice/", absoluteLink.getDirectoryPart());
	}
	
	@Test
	public void testMalformedHttpUrl() throws Exception {
		// A URL whose protocol is "http:" or "https:" MUST
		// have a host part.
		try {
			URL nope = new URL("http:foo.bar/baz.html");
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			// good
		}
	}
	
	@Test
	public void testCopyConstructor() throws Exception {
		URL copyOfAssignmentDesc = new URL(assignmentDesc);
		
		assertEquals(copyOfAssignmentDesc.getProtocol(), assignmentDesc.getProtocol());
		assertEquals(copyOfAssignmentDesc.getHost(), assignmentDesc.getHost());
		assertEquals(copyOfAssignmentDesc.isAbsolute(), assignmentDesc.isAbsolute());
		assertEquals(copyOfAssignmentDesc.isDirectory(), assignmentDesc.isDirectory());
		assertEquals(copyOfAssignmentDesc.isCanonical(), assignmentDesc.isCanonical());
		assertEquals(copyOfAssignmentDesc.getPath(), assignmentDesc.getPath());
	}
	
	@Test
	public void testEquals() throws Exception {
		URL copyOfAssignmentDesc = new URL(ASSIGNMENT_DESC_URL);
		URL copyOfRelativeLink = new URL(RELATIVE_LINK_URL);
		URL copyOfRelativeNonCanonicalLink = new URL(RELATIVE_NON_CANONICAL_LINK_URL); // by itself, cannot be made canonical
		URL copyOfCourseWebsiteDir = new URL(COURSE_WEBSITE_DIR_URL);
		URL copyOfSecureAssignmentDesc = new URL(SECURE_ASSIGNMENT_DESC_URL);
		URL copyOfSecureCourseWebsiteDir = new URL(SECURE_COURSE_WEBSITE_DIR_URL);
		URL copyOfAssignmentDescImplicitProto = new URL(ASSIGNMENT_DESC_IMPLICIT_PROTO_URL);
		URL copyOfRelativeNonCanonicalLink2 = new URL(RELATIVE_NON_CANONICAL_LINK2_URL); // can be made canonical directly
		URL copyOfAssignmentDescNonCanonical = new URL(ASSIGNMENT_DESC_NON_CANONICAL_URL);
		URL copyOfAbsoluteLink = new URL(ABSOLUTE_LINK);
		
		assertEquals(copyOfAssignmentDesc, assignmentDesc);
		assertEquals(copyOfRelativeLink, relativeLink);
		assertEquals(copyOfRelativeNonCanonicalLink, relativeNonCanonicalLink);
		assertEquals(copyOfCourseWebsiteDir, courseWebsiteDir);
		assertEquals(copyOfSecureAssignmentDesc, secureAssignmentDesc);
		assertEquals(copyOfSecureCourseWebsiteDir, secureCourseWebsiteDir);
		assertEquals(copyOfAssignmentDescImplicitProto, assignmentDescImplicitProto);
		assertEquals(copyOfRelativeNonCanonicalLink2, relativeNonCanonicalLink2);
		assertEquals(copyOfAssignmentDescNonCanonical, assignmentDescNonCanonical);
		assertEquals(copyOfAbsoluteLink, absoluteLink);
	}
	
	@Test
	public void testMakeCanonical() throws Exception {
		// make it canonical: it should no longer compare is equal to the original
		URL u = relativeNonCanonicalLink2.makeCanonical();
		assertNotEquals(u, relativeNonCanonicalLink2);
		
		// check data
		assertEquals("", u.getProtocol());
		assertEquals("", u.getHost());
		assertFalse(u.isAbsolute());
		assertFalse(u.isDirectory());
		assertTrue(u.isCanonical());
		assertEquals("foo/blat.html", u.getPath());
	}
	
	@Test
	public void testMakeCanonical2() throws Exception {
		// make it canonical
		URL u = assignmentDescNonCanonical.makeCanonical();
		assertNotEquals(u, assignmentDescNonCanonical);
		
		// It should now be equal to the canonical form of the assignment
		// description URL
		assertEquals(u, assignmentDesc);
		
		// check data
		assertEquals("http:", u.getProtocol());
		assertEquals("ycpcs.github.io", u.getHost());
		assertTrue(u.isAbsolute());
		assertFalse(u.isDirectory());
		assertTrue(u.isCanonical());
		assertEquals("/cs201-fall2016/assign/assign06.html", u.getPath());
	}
	
	@Test
	public void testMakeCanonicalFails() throws Exception {
		// Test calling makeCanonical on a URL that can't
		// be made canonical because it references path components
		// "below" the (implicit) starting location
		try {
			relativeNonCanonicalLink.makeCanonical();
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			// good
		}
	}
	
	@Test
	public void testToString() throws Exception {
		assertEquals(ASSIGNMENT_DESC_URL, assignmentDesc.toString());
		assertEquals(RELATIVE_LINK_URL, relativeLink.toString());
		assertEquals(RELATIVE_NON_CANONICAL_LINK_URL, relativeNonCanonicalLink.toString());
		assertEquals(COURSE_WEBSITE_DIR_URL, courseWebsiteDir.toString());
		assertEquals(SECURE_ASSIGNMENT_DESC_URL, secureAssignmentDesc.toString());
		assertEquals(SECURE_COURSE_WEBSITE_DIR_URL, secureCourseWebsiteDir.toString());
		assertEquals(ASSIGNMENT_DESC_IMPLICIT_PROTO_URL, assignmentDescImplicitProto.toString());
		assertEquals(RELATIVE_NON_CANONICAL_LINK2_URL, relativeNonCanonicalLink2.toString());
		assertEquals(ASSIGNMENT_DESC_NON_CANONICAL_URL, assignmentDescNonCanonical.toString());
	}
	
	@Test
	public void testGetReferencedURL() throws Exception {
		// Find the canonical absolute forms of relative links,
		// using the directory of the assignment description as the
		// starting point.
		URL u = secureAssignmentDesc.getReferencedURL(relativeLink);
		assertEquals("https://ycpcs.github.io/cs201-fall2016/assign/foo/bar/baz.html", u.toString());
		URL u2 = secureAssignmentDesc.getReferencedURL(relativeNonCanonicalLink);
		assertEquals("https://ycpcs.github.io/fizz/buzz.html", u2.toString());
		
		// Find the canonical absolute form of a referenced absolute link.
		URL u3 = secureAssignmentDesc.getReferencedURL(absoluteLink);
		assertEquals("https://ycpcs.github.io/cs201-fall2016/practice/index.html", u3.toString());
	}
}
