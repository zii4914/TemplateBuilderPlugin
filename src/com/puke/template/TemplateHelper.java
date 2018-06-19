//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import com.puke.template.util.FileUtil;
import java.io.File;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class TemplateHelper {
	public TemplateHelper() {
	}

	public static String getPackageName(String manifest) {
		if (manifest != null && manifest.length() != 0) {
			File file = new File(manifest);
			if (file.exists() && file.isFile()) {
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

				try {
					DocumentBuilder builder = builderFactory.newDocumentBuilder();
					Document document = builder.parse(file);
					return document.getFirstChild().getAttributes().getNamedItem("package").getNodeValue();
				} catch (Exception var5) {
					var5.printStackTrace();
					throw new RuntimeException("Can't resolve package name.");
				}
			} else {
				throw new RuntimeException("The manifest file doesn't exist or isn't file.");
			}
		} else {
			throw new IllegalArgumentException("The manifest path is empty.");
		}
	}

	public static String getPackagePath(String packageName) {
		packageName = packageName.replace('.', '/') + '/';
		return packageName;
	}

	public static InputStream getOriginStream(String relativePath) {
		InputStream resource = TemplateHelper.class.getResourceAsStream("/templates/" + relativePath);
		System.out.println("zii-yunmai:"+resource);
		return resource;
	}

	public static byte[] getOriginBytes(String relativePath) {
		return FileUtil.is2Bytes(getOriginStream(relativePath));
	}
}
