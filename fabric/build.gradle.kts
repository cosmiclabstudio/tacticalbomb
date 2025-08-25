plugins {
    `multiloader-loader`
    id("fabric-loom") version "1.11-SNAPSHOT"
    kotlin("jvm") version "2.2.0"
    id("com.google.devtools.ksp") version "2.2.0-2.0.2"
    id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.14"
}

stonecutter {

}

dependencies {
    minecraft("com.mojang:minecraft:${commonMod.mc}")
    mappings(loom.layered {
        officialMojangMappings()
        commonMod.depOrNull("parchment")?.let { parchmentVersion ->
            parchment("org.parchmentmc.data:parchment-${commonMod.mc}:$parchmentVersion@zip")
        }
    })

    modImplementation("net.fabricmc:fabric-loader:${commonMod.dep("fabric_loader")}")
    modApi("net.fabricmc.fabric-api:fabric-api:${commonMod.dep("fabric_api")}+${commonMod.mc}")

    // Required dependencies
    modImplementation("com.terraformersmc:modmenu:${commonMod.dep("modmenu")}")
    modImplementation("dev.isxander:yet-another-config-lib:${commonMod.dep("yacl")}-fabric")
    implementation("me.xdrop:fuzzywuzzy:1.4.0")
    include("me.xdrop:fuzzywuzzy:1.4.0")
}

loom {
    runs {
        getByName("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
        }
        getByName("server") {
            server()
            configName = "Fabric Server"
            ideConfigGenerated(true)
        }
    }
}