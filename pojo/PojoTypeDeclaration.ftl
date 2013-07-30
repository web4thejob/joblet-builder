<#include "Ejb3TypeDeclaration.ftl"/>
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()} extends
<#if !pojo.isSubclass()>
	AbstractHibernateEntity
<#else>
	${pojo.getSuperClass().getDeclarationName()}
</#if>