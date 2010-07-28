package fr.imag.adele.cadse.copycomposer.generator;

import java.util.Collection;
import java.util.Set;

import model.workspace.copycomposer.CopyComposerCST;

import fede.workspace.eclipse.composition.java.IPDEContributor;
import fede.workspace.eclipse.java.JavaIdentifier;
import fr.imag.adele.cadse.as.generator.GGenFile;
import fr.imag.adele.cadse.as.generator.GGenerator;
import fr.imag.adele.cadse.as.generator.GResult;
import fr.imag.adele.cadse.as.generator.GToken;
import fr.imag.adele.cadse.as.generator.GenState;
import fr.imag.adele.cadse.cadseg.ParseTemplate;
import fr.imag.adele.cadse.cadseg.exp.ParseException;
import fr.imag.adele.cadse.cadseg.generator.composer.GComposer;
import fr.imag.adele.cadse.cadseg.managers.build.ComposerManager;
import fr.imag.adele.cadse.cadseg.managers.build.CompositeItemTypeManager;
import fr.imag.adele.cadse.core.GenContext;
import fr.imag.adele.cadse.core.GenStringBuilder;
import fr.imag.adele.cadse.core.Item;

/**
 * @generated
 */
public class GCopyIntoFolderComposer extends GComposer {

	public static final class GCopyIntoFolderComposer_MF extends
			IPDEContributor {
		@Override
		public void computeImportsPackage(Item currentItem, Set<String> imports) {
			imports.add("org.eclipse.core.runtime");
		}

	}

	private String escapeBackSlashes(final String str) {
		return str.replaceAll("\\\\", "\\\\");
	}

	@Override
	public void generatePartFile(GResult sb, Item currentItem, GGenFile gf,
			GToken kind, GenContext context, GGenerator gGenerator,
			GenState state) {

		ComposerManager cm = (ComposerManager) currentItem.getType()
				.getItemManager();
		Set<String> imports = state.getImports();

		final String defaultQualifiedClassName = cm.getDefaultClassName();
		String defaultClassName = JavaIdentifier
				.getlastclassName(defaultQualifiedClassName);

		if ("inner-class".equals(kind)) {
			imports.add("fr.imag.adele.cadse.core.Item");
			imports.add("fr.imag.adele.cadse.core.CadseException");
			imports.add(defaultQualifiedClassName);
			final boolean extendsClass = cm.mustBeExtended()
					|| cm.isExtendsClass(currentItem);
			if (extendsClass) {

				final String extendsClassName = defaultClassName;
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						currentItem.getName(), true, false, "Composer");
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public class ").append(defaultClassName)
						.append(" extends ").append(extendsClassName)
						.append(" {");
				sb.begin();
				sb.newline();
				sb.newline().append("/**");
				sb.newline().append("	@generated");
				sb.newline().append("*/");
				sb.newline().append("public ").append(defaultClassName)
						.append(" (");
				generateConstructorParameter(sb);
				sb.decrementLength();
				sb.append(") {");
				sb.newline().append("	super(");
				generateConstrustorArguments(sb);
				sb.decrementLength();
				sb.append(");");
				sb.newline().append("}");

				imports.add("fr.imag.adele.cadse.core.build.IExporterTarget");

				if (cm.generateGetTargetMethod()) {
					sb.newline();
					sb.newline().append("@Override");
					sb.newline().append(
							"protected IExporterTarget getTarget() {");
					sb.newline().append("	// TODO Auto-generated method stub");
					sb.newline().append("	return null;");
					sb.newline().append("}");

				}

				sb.newline();
				sb.newline().append("@Override");
				sb.newline().append(
						"protected void postCompose(IBuildingContext context,");
				sb.newline().append(
						"		List<IExportedContent> listExportedContent,");
				sb.newline().append("		IExporterTarget target) {");
				sb.newline().append("	// TODO Auto-generated method stub");
				sb.newline()
						.append("	super.postCompose(context, listExportedContent, target);");
				sb.newline().append("}");

				// @added begin
				generateOtherMethods(currentItem, sb, imports, context);
				// @added end

				sb.end();

				sb.newline().append("}");

				imports.add("java.util.List");
				imports.add("fr.imag.adele.cadse.core.build.Composer");
				imports.add("fr.imag.adele.cadse.core.build.IBuildingContext");
				imports.add("fr.imag.adele.cadse.core.build.IExportedContent");

			}
		}

		if ("composers".equals(kind)) {
			final boolean extendsClass = cm.mustBeExtended()
					|| ComposerManager.isExtendsClass(currentItem);

			if (extendsClass) {
				defaultClassName = JavaIdentifier.javaIdentifierFromString(
						currentItem.getName(), true, false, "Composer");
			}

			// @changed
			sb.newline().append("new ").append(defaultClassName)
					.append(" (cm,");
			generateCallArguments(currentItem, sb, imports, context);
			sb.decrementLength();
			sb.append("),");
		}
	}

	@Override
	protected void generateCallArguments(Item currentItem,
			final GenStringBuilder sb, final Set<String> imports,
			final GenContext context) {

		sb.appendStringValue(currentItem.getName()).append(", ");

		super.generateCallArguments(currentItem, sb, imports, context);
	}

	@Override
	protected void generateConstrustorArguments(final GenStringBuilder sb) {
		sb.newline().append("contentItem, name, exporterTypes,");
	}

	@Override
	protected void generateConstructorParameter(final GenStringBuilder sb) {
		sb.append("ContentItem contentItem, String name, String... exporterTypes,");
	}

	/**
	 * @param context
	 * @Added
	 */
	@Override
	protected void generateOtherMethods(final Item currentItem,
			final GResult sb, final Set<String> imports, GenContext context) {

		generateAcceptsMethod(currentItem, sb, imports);
		generateGetTargetPathMethod(currentItem, sb, imports);
	}

	private void generateAcceptsMethod(Item currentItem,
			final GenStringBuilder sb, final Set<String> imports) {
		final Collection<Item> acceptLinkTypes = currentItem.getOutgoingItems(
				CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_lt_MANAGES_LT, true);

		if (acceptLinkTypes.size() == 0) {
			return;
		}

		sb.newline();
		sb.appendGeneratedTag();
		sb.newline().append("@Override");

		imports.add("fr.imag.adele.cadse.core.Link");

		sb.newline().append("protected boolean accepts(Link l) {");
		sb.begin();
		sb.newline().append(
				"// Don't remove this line, necessary for link management !!!");
		sb.newline().append("super.accepts(l);");
		sb.newline();
		sb.newline().append("String linkTypeName = l.getLinkType().getName();");

		imports.add("java.util.List");
		imports.add("java.util.ArrayList");

		sb.newline().append(
				"List<String> linkTypeNames = new ArrayList<String>();");

		// Retrieve all managed link types
		for (final Item item : acceptLinkTypes) {
			sb.newline().append(
					"linkTypeNames.add(\"" + escapeBackSlashes(item.getName())
							+ "\");");
		}

		sb.newline();
		sb.newline().append("return linkTypeNames.contains(linkTypeName);");
		sb.end();
		sb.newline().append("}");
		sb.newline();
	}

	private void generateGetTargetPathMethod(Item currentItem,
			final GenStringBuilder sb, final Set<String> imports) {
		sb.newline();
		sb.appendGeneratedTag();
		sb.newline().append("@Override");

		imports.add("org.eclipse.core.runtime.IPath");

		sb.newline().append("public IPath getTargetPath() {");
		sb.begin();
		sb.newline();

		// Generate evaluation of folder path attribute value expresion
		String value = currentItem
				.getAttribute(CopyComposerCST.COPY_INTO_FOLDER_COMPOSER_at_TARGET_FOLDER_);
		if (value == null || value.length() == 0) {
			value = "";
		}

		final Item compositeItem = currentItem.getPartParent();
		final Item itemtype = CompositeItemTypeManager
				.getItemType(compositeItem);
		final ParseTemplate pt = new ParseTemplate(itemtype, value, null);
		try {
			pt.main();
			pt.build("getItem()", "sb", sb, imports, null);
		} catch (final ParseException e) {
			e.printStackTrace();
		} catch (final Exception ex) {
			ex.printStackTrace();
		}

		imports.add("org.eclipse.core.runtime.Path");

		sb.newline();
		sb.newline().append("return new Path(sb.toString());");
		sb.end();
		sb.newline().append("}");
		sb.newline();
	}

}