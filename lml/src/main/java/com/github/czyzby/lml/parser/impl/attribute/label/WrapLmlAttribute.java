package com.github.czyzby.lml.parser.impl.attribute.label;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.github.czyzby.lml.parser.LmlParser;
import com.github.czyzby.lml.parser.tag.LmlAttribute;
import com.github.czyzby.lml.parser.tag.LmlTag;

/** See {@link Label#setWrap(boolean)}. Mapped to "wrap".
 *
 * @author MJ */
public class WrapLmlAttribute implements LmlAttribute<Label> {
    @Override
    public Class<Label> getHandledType() {
        return Label.class;
    }

    @Override
    public void process(final LmlParser parser, final LmlTag tag, final Label actor, final String rawAttributeData) {
        actor.setWrap(parser.parseBoolean(rawAttributeData, actor));
    }
}
