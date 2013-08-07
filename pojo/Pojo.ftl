${pojo.getPackageDeclaration()}

<#assign classbody>
<#include "PojoTypeDeclaration.ftl"/> {

<#if !pojo.isInterface()>
<#include "PojoFields.ftl"/>

<#include "PojoPropertyAccessors.ftl"/>

<#include "PojoToString.ftl"/>

<#include "PojoEqualsHashcode.ftl"/>

<#else>
<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>
<#include "PojoExtraClassCode.ftl"/>

}
</#assign>

${pojo.generateImports()}

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.web4thejob.context.ContextUtil;
import org.hibernate.validator.constraints.NotBlank;

<#if !pojo.isSubclass()>
	import org.web4thejob.orm.AbstractHibernateEntity;
<#else>
	import ${pojo.getSuperClass().getPackageName()}.${pojo.getSuperClass().getDeclarationName()};
</#if>

${classbody}
 