<#if pojo.hasMetaAttribute("class-code")>  // The following is extra code specified in the hbm.xml files
${pojo.getExtraClassCode()}
</#if>

<#if !pojo.isSubclass()>

@Override
public Serializable getIdentifierValue() {
	return ${pojo.getIdentifierProperty().getName()};
}

@Override
public void setAsNew() {		
	<#if pojo.getJavaTypeName(pojo.getIdentifierProperty(), jdk5)=='String'>
		${pojo.getIdentifierProperty().getName()} = null;		
	<#elseif pojo.getJavaTypeName(pojo.getIdentifierProperty(), jdk5)=='Long'>
		${pojo.getIdentifierProperty().getName()} = 0L;
	<#else>
		${pojo.getIdentifierProperty().getName()} = 0;
	</#if>
}

</#if>