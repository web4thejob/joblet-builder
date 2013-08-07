<#if pojo.needsToString()>
	 @Override    
     public String toString() {	  	
	  	StringBuilder buffer = new StringBuilder();      
		<#foreach property in pojo.getToStringPropertiesIterator()>
		
				if (buffer.length()>0){
					buffer.append(" / ");
				}		   
			<#if property.getType().isAssociationType()>
				if (${pojo.getGetterSignature(property)}() != null) {
					buffer.append(ContextUtil.getMRS().deproxyEntity(${property.name}).toString());
				} else {
					buffer.append("?");									
				}
			<#else>   
				buffer.append(${property.name});
			</#if>			
		</#foreach>      
      
      return buffer.toString();
     }
</#if>	  	
