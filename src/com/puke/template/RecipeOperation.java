//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.util.Iterator;
import java.util.Locale;

public class RecipeOperation extends TextOperation {
	private static final String END_TAG = "</recipe>";
	private static final String BUILD_GRADLE = "build.gradle";
	private BuildGradleOperation buildGradleOperation;

	public RecipeOperation(Target target) {
		super(target);
	}

	protected String handleText(String originContent) {
		this.fillReplacement();
		return super.handleText(originContent);
	}

	public byte[] getCurrentContent() {
		byte[] bytes = super.getCurrentContent();
		return this.handleBuildGradle(bytes, "build.gradle.ftl");
	}

	protected byte[] handleByte(byte[] bytes) {
		return this.handleBuildGradle(super.handleByte(bytes), "<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />");
	}

	private byte[] handleBuildGradle(byte[] bytes, String buildGradleFlag) {
		String result = bytes == null ? "" : new String(bytes);
		if (this.buildGradleOperation.isEmpty()) {
			if (result.contains("<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />")) {
				result = result.replace("<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />", "");
			}
		} else if (!result.contains(buildGradleFlag) && result.contains("</recipe>")) {
			result = result.replace("</recipe>", String.format(Locale.getDefault(), "\t%s\n%s", "<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />", "</recipe>"));
		}

		return result.getBytes();
	}

	private void fillReplacement() {
		StringBuilder builder = new StringBuilder();
		String packageName = TemplateHelper.getPackageName(this.target.getManifest());
		this.fillJavaReplacement(builder, packageName);
		this.fillLayoutReplacement(builder);
		this.fillDrawableReplacement(builder);
		String mergeBuildGradle = this.buildGradleOperation.isEmpty() ? "" : "<merge from=\"root/build.gradle.ftl\"\n\t\tto=\"${escapeXmlAttribute(projectOut)}/build.gradle\" />";
		this.newReplacementChain().append(builder.toString()).append("mergeBuildGradle", mergeBuildGradle);
	}

	private void fillJavaReplacement(StringBuilder builder, String packageName) {
		String packagePath = TemplateHelper.getPackagePath(packageName);
		Iterator var4 = this.target.getJavaFiles().iterator();

		while(var4.hasNext()) {
			String javaFile = (String)var4.next();
			String relativePath = javaFile.split(packagePath, 2)[1];
			builder.append(RecipeFormat.getJavaItem(relativePath));
		}

	}

	private void fillLayoutReplacement(StringBuilder builder) {
		Iterator var2 = this.target.getLayoutFiles().iterator();

		while(var2.hasNext()) {
			String layoutFile = (String)var2.next();
			String relativePath = layoutFile.split("src/main/res/layout/", 2)[1];
			builder.append(RecipeFormat.getLayoutItem(relativePath));
		}

	}

	private void fillDrawableReplacement(StringBuilder builder) {
		Iterator var2 = this.target.getDrawableFiles().iterator();

		while(var2.hasNext()) {
			String drawableFile = (String)var2.next();
			String relativePath = drawableFile.split("src/main/res/", 2)[1];
			builder.append(RecipeFormat.getResItem(relativePath));
		}

	}

	protected String getFileName() {
		return "recipe.xml.ftl";
	}
}
