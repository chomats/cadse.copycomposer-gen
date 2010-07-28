package fr.imag.adele.cadse.copycomposer.generator;

import fr.imag.adele.cadse.cadseg.generator.exporter.GExporter;
import fr.imag.adele.cadse.core.GenStringBuilder;

/**
 * @generated
 */
public class GJavaRefExporter extends GExporter {

	@Override
	protected void generateConstrustorArguments(final GenStringBuilder sb) {
		sb.append("contentItem");
	}

	@Override
	protected void generateConstructorParameter(final GenStringBuilder sb) {
		sb.append("ContentItem contentItem");
	}
}