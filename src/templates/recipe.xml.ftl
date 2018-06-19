<?xml version="1.0"?>
<recipe>`default`

    <merge from="root/AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    `mergeBuildGradle`
</recipe>
