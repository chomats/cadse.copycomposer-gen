/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 * Copyright (C) 2006-2010 Adele Team/LIG/Grenoble University, France
 */
package fr.imag.adele.cadse.copycomposer.generator;

import java.util.UUID;

import model.workspace.copycomposer.CopyComposerCST;

import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;

import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.IGenerator;
import fr.imag.adele.cadse.as.generator.IRuntimeGenerator;
import fr.imag.adele.cadse.cadseg.generator.GCadseGenerator;
import fr.imag.adele.cadse.core.CadseGCST;

@Component(name = "fr.imag.adele.cadse.copyComposerGenerator", immediate = true, architecture = true)
@Provides(specifications = { IGenerator.class })
@Instantiate(name = "fr.imag.adele.cadse.copyComposerGenerator.instance")
public class CopyComposerGenerator extends GGenerator {

	public static final UUID ID = UUID.fromString("C4989870-B3CD-4CAD-9289-B28D6E252E8E");

	public CopyComposerGenerator() {
		super(ID);
	}

	@Override
	public void load(IRuntimeGenerator runtimeGenerator) {
	//	CopyComposerCST.JAVA_REF_COMPOSER.addAdapter(new GJavaRefComposer.GJavaRefComposer_MF());
	//	CopyComposerCST.JAVA_REF_COMPOSER.addAdapter(new GJavaRefComposer.GJavaRefComposer_MF());
	}

	@Override
	public UUID[] getRequireGenerator() {
		return new UUID[] { GCadseGenerator.ID };
	}

	@Override
	public UUID[] getRequireCadse() {
		return new UUID[] { CadseGCST._CADSE_ID, CopyComposerCST._CADSE_ID };
	}
}
