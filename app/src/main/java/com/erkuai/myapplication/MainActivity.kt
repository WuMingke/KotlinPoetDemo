package com.erkuai.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.kotlinpoet.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val greeterClass = ClassName("", "Greeter")
        val file = FileSpec.builder("", "HelloWorld")
            .addType(
                TypeSpec.classBuilder("Greeter")
                    .primaryConstructor(
                        FunSpec.constructorBuilder()
                            .addParameter("name", String::class.java)
                            .build()
                    )
                    .addProperty(
                        PropertySpec.builder("name", String::class.java)
                            .initializer("name")
                            .build()
                    )
                    .addFunction(
                        FunSpec.builder("greet")
                            .addStatement("prinltn(%P)", "Hello,\$name")
                            .build()
                    ).build()
            ).addFunction(
                FunSpec.builder("main")
                    .addParameter("args", String::class.java, KModifier.VARARG)
                    .addStatement("%T(args[0]).greet()", greeterClass)
                    .build()
            ).build()

        file.writeTo(System.out)
    }
}