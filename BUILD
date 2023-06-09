java_binary(
    name = "data-migrator",
    main_class= "math_genealogy.DataMigrator",
    srcs=["DataMigrator.java"],
    deps =
        [
                "@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
                "@vaticle_typedb_client_java//api",
                "@vaticle_typeql//java:typeql-lang",
                "@vaticle_typeql//java/query"
        ],
    data =
        [
            ":data.json",
            ":datalite.json",
            ":schema.tql"
        ]
)
java_test(
    name="data-loader-test",
    test_class="math_genealogy.DataLoaderTest",
    srcs=["DataLoaderTest.java"],
    deps =
        [
            "@maven//:org_sharegov_mjson","@vaticle_typedb_client_java//:client-java",
            "@vaticle_typedb_client_java//api",
            "@vaticle_typeql//java:typeql-lang",
            "@vaticle_typeql//java/query", ":data-migrator"
        ]
)

load("@vaticle_typedb_common//test:rules.bzl", "native_typedb_artifact")
load("@vaticle_bazel_distribution//artifact:rules.bzl", "artifact_extractor")

native_typedb_artifact(
    name = "native-typedb-artifact",
    mac_artifact = "@vaticle_typedb_artifact_mac//file",
    linux_artifact = "@vaticle_typedb_artifact_linux//file",
    windows_artifact = "@vaticle_typedb_artifact_windows//file",
    output = "typedb-server-native.tar.gz",
    visibility = ["//test:__subpackages__"],
)

artifact_extractor(
    name = "typedb-extractor",
    artifact = ":native-typedb-artifact",
)
