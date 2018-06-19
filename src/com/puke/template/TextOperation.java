//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class TextOperation extends Operation {
	protected static final String SRC_MAIN_RES = "src/main/res/";
	protected static final String SRC_MAIN_RES_LAYOUT = "src/main/res/layout/";
	private static final String REPLACEMENT = "`%s`";
	private static final String EXPR_DEFAULT = "default";
	private final Map<String, String> replacements = new HashMap();

	public TextOperation(Target target) {
		super(target);
	}

	public void setText(String text) {
		this.setCurrentContent((text == null ? "" : text).getBytes());
	}

	public String getText() {
		return new String(this.getCurrentContent());
	}

	public boolean isEmpty() {
		String content = this.getText();
		return content == null || content.trim().length() == 0;
	}

	protected byte[] handleByte(byte[] bytes) {
		String originContent = new String(bytes);
		String handledContent = this.handleText(originContent);
		if (handledContent == null) {
			handledContent = "";
		}

		return handledContent.getBytes();
	}

	protected String handleText(String originContent) {
		if (this.replacements.size() == 0) {
			return originContent;
		} else {
			String result = originContent;
			Iterator var3 = this.replacements.keySet().iterator();

			while(var3.hasNext()) {
				String key = (String)var3.next();
				String value = (String)this.replacements.get(key);
				if (value == null) {
					value = "";
				}

				String replacement = this.getReplaceExpr(key);
				if (originContent.contains(replacement)) {
					result = result.replace(replacement, value);
				}
			}

			return result;
		}
	}

	public String getReplaceExpr(String replace) {
		return String.format("`%s`", replace);
	}

	public ReplacementChain newReplacementChain() {
		return new TextOperation.ReplacementChainImpl();
	}

	private class ReplacementChainImpl implements ReplacementChain {
		private ReplacementChainImpl() {
		}

		public ReplacementChain append(String from, String to) {
			TextOperation.this.replacements.put(from, to);
			return this;
		}

		public ReplacementChain append(String to) {
			return this.append("default", to);
		}
	}
}
