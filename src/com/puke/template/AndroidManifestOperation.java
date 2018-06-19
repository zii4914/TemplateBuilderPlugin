//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.puke.template;

import java.util.Iterator;
import java.util.List;

public class AndroidManifestOperation extends TextOperation {
    private static final String TEMPLATE_ACTIVITY_ITEM = "\n\t\t<activity android:name=\"${relativePackage}%s\"/>";

    public AndroidManifestOperation(Target target) {
        super(target);
    }

    protected String handleText(String originContent) {
        StringBuilder builder = new StringBuilder();
        this.appendActivity(builder);
        this.newReplacementChain().append(builder.toString());
        return super.handleText(originContent);
    }

    private void appendActivity(StringBuilder builder) {
        List<String> activityItems = this.target.getActivityItems();
        Iterator var3 = activityItems.iterator();

        while(var3.hasNext()) {
            String activityItem = (String)var3.next();
            builder.append(String.format("\n\t\t<activity android:name=\"${relativePackage}%s\"/>", activityItem));
        }

    }

    protected String getFileName() {
        return "root/AndroidManifest.xml.ftl";
    }
}
