package com.github.czyzby.autumn.mvc.component.ui.processor;

import java.lang.annotation.Annotation;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.github.czyzby.autumn.annotation.field.Inject;
import com.github.czyzby.autumn.annotation.stereotype.Component;
import com.github.czyzby.autumn.annotation.stereotype.MetaComponent;
import com.github.czyzby.autumn.context.ContextComponent;
import com.github.czyzby.autumn.context.ContextContainer;
import com.github.czyzby.autumn.context.processor.type.ComponentTypeAnnotationProcessor;
import com.github.czyzby.autumn.mvc.component.ui.InterfaceService;
import com.github.czyzby.autumn.mvc.component.ui.controller.ViewDialogController;
import com.github.czyzby.autumn.mvc.component.ui.controller.impl.AnnotatedViewDialogController;
import com.github.czyzby.autumn.mvc.stereotype.ViewDialog;
import com.github.czyzby.kiwi.util.gdx.asset.lazy.Lazy;
import com.github.czyzby.kiwi.util.gdx.reflection.Reflection;

/** Processes {@link com.github.czyzby.autumn.mvc.stereotype.ViewDialog} components. Initiates dialog
 * controllers.
 *
 * @author MJ */
@MetaComponent
public class ViewDialogAnnotationProcessor extends ComponentTypeAnnotationProcessor {
	@Inject(lazy = InterfaceService.class)
	private Lazy<InterfaceService> interfaceService;

	@Override
	public Class<? extends Annotation> getProcessedAnnotationClass() {
		return ViewDialog.class;
	}

	@Override
	public void processClass(final ContextContainer context, final Class<?> componentClass) {
		final boolean isInContext = context.contains(componentClass);
		final ContextComponent component =
				isInContext ? context.extractFromContext(componentClass) : prepareComponent(context,
						componentClass);
		final Object controller = component.getComponent();
		final ViewDialog dialogData = Reflection.getAnnotation(componentClass, ViewDialog.class);
		if (controller instanceof ViewDialogController) {
			interfaceService.get().registerDialogController(controller.getClass(),
					(ViewDialogController) controller);
		} else {
			interfaceService.get().registerDialogController(controller.getClass(),
					new AnnotatedViewDialogController(dialogData, controller, interfaceService.get()));
		}
		if (!isInContext) {
			context.registerComponent(component);
		}
	}

	@Override
	public ContextComponent prepareComponent(final ContextContainer context, final Class<?> componentClass) {
		if (ClassReflection.isAnnotationPresent(componentClass, Component.class)) {
			final Component componentData = Reflection.getAnnotation(componentClass, Component.class);
			return new ContextComponent(componentClass, getNewInstanceOf(componentClass),
					componentData.lazy(), componentData.keepInContext());
		}
		// Not lazy and kept in context by default.
		return new ContextComponent(componentClass, getNewInstanceOf(componentClass), false, true);
	}
}