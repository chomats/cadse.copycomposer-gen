package fr.imag.adele.cadse.copycomposer.generator;

import java.util.Set;

import model.workspace.copycomposer.CopyComposerCST;
import model.workspace.copycomposer.managers.FileRefExporterManager;
import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fede.workspace.eclipse.java.manager.JavaFileContentManager;
import fr.imag.adele.cadse.as.generator.GCst;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.generator.exporter.GExporter;
import fr.imag.adele.cadse.cadseg.managers.build.exporter.ExporterManager;
import fr.imag.adele.cadse.cadseg.managers.content.ManagerManager;
import fr.imag.adele.cadse.cadseg.managers.dataModel.ItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class GFileRefExporter extends GExporter {

	public static class GFileRefExporter_MF extends IPDEContributor {

		@Override
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			imports.add("org.eclipse.core.resources");
			imports.add("fede.workspace.eclipse");
		}
	}

	@Override
	protected void generateConstrustorArguments(final GenStringBuilder sb, Item currentItem) {
		sb.append("contentManager,null,null,exportTypes");
	}

	@Override
	protected void generateConstructorParameter(final GenStringBuilder sb) {
		sb.append("ContentItem contentManager, String... exportTypes");
	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {

		FileRefExporterManager cm = (FileRefExporterManager) currentItem
				.getType().getItemManager();
		Set<String> imports = state.getImports();

		// ItemType it = getItem().getType();
		if (kind.abs() == GCst.t_cstes) {
			if (cm.mustExtendExportItemMethod()) {
				generateExportItemMethod(sb);
			}
			generateOtherMethods(currentItem, sb, imports, context);
		}
	}

	/**
	 * @Added
	 */
	private void generateOtherMethods(Item currentItem,
			final GenStringBuilder sb, final Set<String> imports,
			final GenContext context) {

		sb.newline();
		sb.begin();
		sb.appendGeneratedTag();
		sb.newline().append("@Override");

		imports.add("org.eclipse.core.resources.IFolder");

		sb.newline().append("protected IFolder getExportedFolder() {");
		sb.begin();
		sb.newline();

		// Generate evaluation of folder attribute value expresion
		String value = currentItem
				.getAttribute(CopyComposerCST.FILE_REF_EXPORTER_at_EXPORTED_FOLDER_);
		if (value == null || value.length() == 0) {
			value = "";
		}
		final Item itemtype = ManagerManager.getItemType(currentItem
				.getPartParent());
		final ParseTemplate pt = new ParseTemplate(itemtype, value, null);
		try {
			pt.main();
			pt.build("getItem()", "sb", sb, imports, null);
		} catch (final ParseException e) {
			e.printStackTrace();
		} catch (final Exception ex) {
			ex.printStackTrace();
		}

		// Append computation of folder
		imports.add("org.eclipse.core.resources.IProject");
		imports.add("fede.workspace.eclipse.MelusineProjectManager");
		sb.newline().append(
				"IProject componentProject = "
						+ "MelusineProjectManager.getProject(getItem());");

		sb.newline()
				.append("return componentProject.getFolder(sb.toString());");
		sb.end();
		sb.newline().append("}");
		sb.newline();
		sb.end();
	}

	/**
	 * @Added
	 */
	private void generateExportItemMethod(final GenStringBuilder sb) {
		sb.newline().newline().append("@Override");
		sb.newline()
				.append("public IExportedContent exportItem(IBuildingContext context, "
						+ "IExporterTarget target, String exporterType) throws CadseException {");
		sb.newline().append("	// TODO Auto-generated method stub");
		sb.newline().append("	return null;");
		sb.newline().append("}");
		sb.newline();
	}

}