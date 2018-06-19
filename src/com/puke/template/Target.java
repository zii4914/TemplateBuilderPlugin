//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.util.ArrayList;
import java.util.List;

public class Target {
	private List<String> javaFiles;
	private List<String> activityItems;
	private List<String> layoutFiles;
	private List<String> drawableFiles;
	private String manifest;

	public Target() {
	}

	public List<String> getJavaFiles() {
		if (this.javaFiles == null) {
			this.javaFiles = new ArrayList();
		}

		return this.javaFiles;
	}

	public void setJavaFiles(List<String> javaFiles) {
		this.javaFiles = javaFiles;
	}

	public List<String> getActivityItems() {
		if (this.activityItems == null) {
			this.activityItems = new ArrayList();
		}

		return this.activityItems;
	}

	public void setActivityItems(List<String> activityItems) {
		this.activityItems = activityItems;
	}

	public List<String> getLayoutFiles() {
		if (this.layoutFiles == null) {
			this.layoutFiles = new ArrayList();
		}

		return this.layoutFiles;
	}

	public void setLayoutFiles(List<String> layoutFiles) {
		this.layoutFiles = layoutFiles;
	}

	public List<String> getDrawableFiles() {
		if (this.drawableFiles == null) {
			this.drawableFiles = new ArrayList();
		}

		return this.drawableFiles;
	}

	public void setDrawableFiles(List<String> drawableFiles) {
		this.drawableFiles = drawableFiles;
	}

	public String getManifest() {
		return this.manifest;
	}

	public void setManifest(String manifest) {
		this.manifest = manifest;
	}

	public String toString() {
		return "Target{javaFiles=" + this.javaFiles + ", activityItems=" + this.activityItems + ", layoutFiles=" + this.layoutFiles + ", drawableFiles=" + this.drawableFiles + ", manifest='" + this.manifest + '\'' + '}';
	}
}
