package fr.imag.adele.cadse.copycomposer.generator;

import java.util.Set;

import model.workspace.copycomposer.CopyComposerCST;
import model.workspace.copycomposer.managers.JavaRefComposerManager;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.generator.composer.GComposer;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class GJavaRefComposer extends GComposer {

	public static class GJavaRefComposer_MF extends IPDEContributor {
		@Override
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			imports.add("org.eclipse.core.runtime");
			final boolean has_sources = JavaRefComposerManager
					.hasSources(currentItem);
			final boolean has_classes = JavaRefComposerManager
					.hasClasses(currentItem);

			if (has_classes || has_sources) {
				imports.add("fede.workspace.eclipse.composition.copy.composer");
			}
		}
	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {

		final boolean has_sources = JavaRefComposerManager
				.hasSources(currentItem);
		final boolean has_classes = JavaRefComposerManager
				.hasClasses(currentItem);
		final boolean has_aspects = JavaRefComposerManager
				.hasAspects(currentItem);

		final String classcomposer = "fede.workspace.eclipse.composition.copy.composer.JavaClassesCopyComposer";
		String sourcecompser = "fede.workspace.eclipse.composition.copy.composer.JavaSourcesCopyComposer";
		final String ajsourcecompser = "fede.workspace.eclipse.composition.copy.composer.AspectJSourcesCopyComposer";
		if ("composers".equals(kind)) {

			final String classFolder = currentItem
					.getAttribute(CopyComposerCST.JAVA_REF_COMPOSER_at_FOLDER_CLASSES_);

			boolean classFolderSpecified = (classFolder != null && classFolder
					.length() > 0);

			final String sourceFolder = currentItem
					.getAttribute(CopyComposerCST.JAVA_REF_COMPOSER_at_FOLDER_SOURCES_);

			boolean sourceFolderSpecified = (sourceFolder != null || sourceFolder
					.length() > 0);

			// @changed
			if (has_classes) {
				sb.newline().append("new ").append(classcomposer)
						.append(" (cm");
				if (classFolderSpecified) {
					sb.append(",\"").append(classFolder).append("\"");
					if (sourceFolderSpecified)
						sb.append(",\"").append(sourceFolder).append("\"");
				}

				sb.append("),");
				state.getImports().add(classcomposer);
			}
			if (has_sources) {
				if (has_aspects) {
					sourcecompser = ajsourcecompser;
				}

				sb.newline().append("new ").append(sourcecompser)
						.append(" (cm,").append(!has_classes);
				if (sourceFolderSpecified) {
					sb.append(",\"").append(sourceFolder).append("\"");
				}

				sb.append("),");
				state.getImports().add(sourcecompser);
			}

		}
	}
}