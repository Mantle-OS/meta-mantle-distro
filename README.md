# Mantle OS

**Development status: Pre-alpha**

Mantle OS is an OpenEmbedded-based Linux distribution designed to support a broad range of hardware architectures, programming languages, development toolchains, and software ecosystems.

One of Mantle OS's goals is to make it straightforward for OEMs to build, package, and deploy software written in different programming languages using a consistent OpenEmbedded workflow.

## Programming Language Support

Mantle OS aims to provide a broad, well-integrated development environment rather than requiring OEMs to maintain separate build infrastructures for every language.

Language support is tracked across several distinct capabilities:

- **Toolchain:** A compiler or interpreter is available.
- **Native build:** Tools can execute in the OpenEmbedded build environment.
- **Target runtime:** Applications can run on supported target architectures.
- **SDK:** Development tools are available through a generated SDK.
- **Verified:** The toolchain and example application have passed build or runtime tests.

A language being available in an upstream OpenEmbedded layer does not automatically mean it has been validated on every Mantle OS target.

### Native and Systems Languages

| Language | Current integration |
|---|---|
| C | OE-Core / GCC |
| C++ | OE-Core / GCC |
| Objective-C / Objective-C++ | Verification pending |
| Fortran | GCC toolchain |
| Rust | OE-Core and Mantle integration |
| Go | OE-Core |
| Swift | Investigation pending |
| Zig | Investigation pending |
| D | Investigation pending |
| Ada | Verification pending |
| Nim | Investigation pending |
| Crystal | Investigation pending |

### JVM Languages

| Language | Current integration |
|---|---|
| Java | Mantle OpenJDK 8, 11, 17, 21, and 25 recipes |
| Kotlin | Mantle Kotlin native compiler and `kotlin.bbclass` |
| Scala | Investigation pending |
| Groovy | Investigation pending |
| Clojure | Investigation pending |

**Java build integration:** Mantle provides `java.bbclass`, `maven.bbclass`, `gradle.bbclass`, and `kotlin.bbclass`.

### Managed and Scripting Languages

| Language | Integration or investigation |
|---|---|
| Python 3 | OE-Core and supporting layers |
| Python 2 | Legacy support requiring separate verification |
| Ruby | OpenEmbedded |
| Perl | OpenEmbedded |
| PHP | OpenEmbedded |
| Lua | OpenEmbedded |
| Tcl | OpenEmbedded |
| JavaScript | Node.js ecosystem |
| TypeScript | Node.js tooling; integration verification pending |
| C# / F# / Visual Basic .NET | .NET/Mono investigation |
| PowerShell | Investigation pending |
| Dart | Flutter/Dart layer investigation |

### Functional, Logic, and Scientific Languages

Haskell, OCaml, Erlang, Elixir, Prolog, Common Lisp, Scheme, Racket, Emacs Lisp, R, and Julia are tracked separately to establish actual toolchain availability, architecture coverage, and integration status.

Erlang and Elixir are supported through the integrated `meta-erlang` layer. Other languages in this group require individual verification before a support claim is made.

### Shell and System Utilities

Mantle also includes tooling for POSIX shell, Bash, Dash, Zsh, AWK, and Sed. Fish and KornShell support remain subjects for investigation.

## Build Systems and Development Tools

Programming language support extends beyond having a compiler.

Mantle integrates build systems and developer tools through OpenEmbedded recipes and classes.

| Tool | Purpose |
|---|---|
| GCC | C, C++, Fortran, and supported GCC language frontends |
| OpenJDK | Java compilation and execution |
| Maven | JVM project builds |
| Gradle 8 / 9 | JVM and multi-language project builds |
| Kotlin compiler | Kotlin/JVM compilation |
| CMake | Cross-platform project builds |
| Meson | Cross-platform project builds |
| Cargo | Rust package builds |
| Go tooling | Go application builds |

Colm, Ragel, and other compiler-generation tools are tracked here rather than counted as standalone general-purpose language environments.

## OEM Recipe Examples

Example recipes are maintained under `recipes-examples/`.

These recipes demonstrate how OEM developers can integrate their own projects with Mantle's OpenEmbedded build infrastructure.

**Examples are educational build references and should not be installed in production images merely because they are available.**

