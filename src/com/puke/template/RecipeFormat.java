//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

public final class RecipeFormat {
	private static final String FORMAT_RES = "\n\n\t<%s from=\"root/res/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(resOut)}/%s\" />";
	private static final String FORMAT_LAYOUT = "\n\n\t<instantiate from=\"root/res/layout/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(resOut)}/layout/%s\" />";
	private static final String FORMAT_JAVA = "\n\n\t<instantiate from=\"root/src/app_package/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(srcOut)}/%s\" />";
	public static final String MERGE_BUILD_GRADLE = "<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />";

	public RecipeFormat() {
	}

	public static String getResItem(String relativePath) {
		boolean isTextFile = relativePath.endsWith(".java") || relativePath.endsWith(".xml");
		String operation = isTextFile ? "instantiate" : "copy";
		return String.format("\n\n\t<%s from=\"root/res/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(resOut)}/%s\" />", operation, relativePath, relativePath);
	}

	public static String getLayoutItem(String relativePath) {
		return String.format("\n\n\t<instantiate from=\"root/res/layout/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(resOut)}/layout/%s\" />", relativePath, "activity_${layout_name}.xml");
	}

	public static String getJavaItem(String relativePath) {
		return String.format("\n\n\t<instantiate from=\"root/src/app_package/%s.ftl\"\n\t\tto=\"${escapeXmlAttribute(srcOut)}/%s\" />", relativePath, "${activity_name}Activity.java");
	}
}
