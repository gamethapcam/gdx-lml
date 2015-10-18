package com.github.czyzby.lml.parser.impl.tag.actor;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.impl.tag.builder.TextLmlActorBuilder;
import com.github.czyzby.lml.parser.tag.LmlTag;
import com.github.czyzby.lml.scene2d.ui.reflected.ReflectedLmlDialog;

/** Handles {@link Dialog} actor. Works like a window, except it supports on result action and is attached to the stage
 * with {@link Dialog#show(com.badlogic.gdx.scenes.scene2d.Stage)}. Mapped to "dialog", "popup".
 *
 * @author MJ */
public class DialogLmlTag extends WindowLmlTag {
    public DialogLmlTag(final LmlParser parser, final LmlTag parentTag, final String rawTagData) {
        super(parser, parentTag, rawTagData);
    }

    @Override
    protected Dialog getNewInstanceOfWindow(final TextLmlActorBuilder builder) {
        return new ReflectedLmlDialog(builder.getText(), getSkin(builder), builder.getStyleName());
    }

    @Override
    protected Class<?> getActorType() {
        return Dialog.class;
    }
}