package me.dreamerzero.miniplaceholders.api.annotation.processor;

import com.google.gson.Gson;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import me.dreamerzero.miniplaceholders.api.annotation.Extension;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes({"me.dreamerzero.miniplaceholders.api.annotation.Extension"})
public class ExtensionAnnotationProcessor extends AbstractProcessor {
    private ProcessingEnvironment environment;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.environment = processingEnv;
    }

    @Override
    public synchronized boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(Extension.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                return false;
            }

            Extension extension = element.getAnnotation(Extension.class);
            Name name = ((TypeElement) element).getQualifiedName();

            try {
                FileObject object = environment.getFiler()
                    .createResource(StandardLocation.CLASS_OUTPUT, "", "miniplaceholders-extension.json");
                try (Writer writer = new BufferedWriter(object.openWriter())) {
                    new Gson().toJson(new ProcessedExtension(
                        extension.name(),
                        extension.version(),
                        name.toString(),
                        extension.platform()),
                    writer);
                }
            } catch (IOException e) {
                environment.getMessager().printMessage(Diagnostic.Kind.ERROR, "Cannot generate extension file");
            }
        }
        return false;
    }
}
