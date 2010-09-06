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


@Component(name = "fr.imag.adele.cadse.copycomposer.generator", immediate = true, architecture = true)
@Provides(specifications = { IGenerator.class })
@Instantiate(name="fr.imag.adele.cadse.copycomposer.generator.instance")
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
