<h1 align="center">
  <img src="assets/img/marathon-banner.svg" alt="Marathon Logo" />
</h1>

<div align="center">

[![GitHub release][img-version-badge]][repo] [![Slack][img-slack-badge]][slack] [![Telegram][img-telegram-badge]][telegram] [![PRs Welcome][prs-badge]][prs]

[Releases][release]&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;[Documentation][docs]&nbsp;&nbsp;&nbsp;

</div>

Gradle plugin wrapper for [**Marathon**][marathon] test runner

## TL;DR
Marathon is a fast, platform-independent test runner focused on performance and stability.

Marathon implements multiple key concepts of test execution such as test **batching**, **device pools**, test **sharding**, test **sorting**, **preventive retries** as well as **post-factum retries**. By default, most of these are set to conservative defaults but custom configurations are encouraged for those who want to optimize performance and/or stability.

Marathon's primary focus is on **full control over the balance between stability of test execution, testing performance and cost**.

For more information see the [documentation][docs]

### Overview

#### Tradeoffs using Gradle Plugin

| Pros                                     | Cons                                                                                      |
|------------------------------------------|-------------------------------------------------------------------------------------------|
| Configuration using Gradle syntax        | Requires project sync before testing starts                                               |
| No installation of marathon CLI required | Less flexibility in choosing AGP+Gradle versions. CLI is independent of your Gradle setup |
|                                          | Harder to manage when you have more than 1 test run configuration                         |
|                                          | Missing features, e.g. multi-module testing                                               |


### Configuration

Marathon gradle plugin is published to [plugins.gradle.org][plugins-gradle].
To apply the plugin:

#### Gradle KTS

```kotlin
plugins {
  id("com.malinskiy.marathon") version "X.X.X"
}
```

#### Gradle
```groovy
plugins {
  id 'com.malinskiy.marathon' version 'X.X.X'
}
```

All the test tasks will start with **marathon** prefix, for example **marathonDebugAndroidTest**.


## Contributing

See [contributing docs][contributing]

## License

Marathon codebase is GPL 2.0 [LICENSE][LICENSE]

<!--
Repo References
-->
[repo]:https://github.com/MarathonLabs/marathon-gradle-plugin

<!--
Link References
-->
[img-version-badge]:https://img.shields.io/github/release/MarathonLabs/marathon-gradle-plugin.svg?style=for-the-badge
[img-slack-badge]:https://img.shields.io/badge/Chat-Slack-49c39e?style=for-the-badge
[img-telegram-badge]:https://img.shields.io/badge/Chat-Telegram-0088CC?style=for-the-badge

[slack]:https://bit.ly/2LLghaW
[telegram]:https://t.me/marathontestrunner
[prs-badge]: https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=for-the-badge&logo=data%3Aimage%2Fsvg%2Bxml%3Bbase64%2CPD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48c3ZnIGlkPSJzdmcyIiB3aWR0aD0iNjQ1IiBoZWlnaHQ9IjU4NSIgdmVyc2lvbj0iMS4wIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciPiA8ZyBpZD0ibGF5ZXIxIj4gIDxwYXRoIGlkPSJwYXRoMjQxNyIgZD0ibTI5Ny4zIDU1MC44N2MtMTMuNzc1LTE1LjQzNi00OC4xNzEtNDUuNTMtNzYuNDM1LTY2Ljg3NC04My43NDQtNjMuMjQyLTk1LjE0Mi03Mi4zOTQtMTI5LjE0LTEwMy43LTYyLjY4NS01Ny43Mi04OS4zMDYtMTE1LjcxLTg5LjIxNC0xOTQuMzQgMC4wNDQ1MTItMzguMzg0IDIuNjYwOC01My4xNzIgMTMuNDEtNzUuNzk3IDE4LjIzNy0zOC4zODYgNDUuMS02Ni45MDkgNzkuNDQ1LTg0LjM1NSAyNC4zMjUtMTIuMzU2IDM2LjMyMy0xNy44NDUgNzYuOTQ0LTE4LjA3IDQyLjQ5My0wLjIzNDgzIDUxLjQzOSA0LjcxOTcgNzYuNDM1IDE4LjQ1MiAzMC40MjUgMTYuNzE0IDYxLjc0IDUyLjQzNiA2OC4yMTMgNzcuODExbDMuOTk4MSAxNS42NzIgOS44NTk2LTIxLjU4NWM1NS43MTYtMTIxLjk3IDIzMy42LTEyMC4xNSAyOTUuNSAzLjAzMTYgMTkuNjM4IDM5LjA3NiAyMS43OTQgMTIyLjUxIDQuMzgwMSAxNjkuNTEtMjIuNzE1IDYxLjMwOS02NS4zOCAxMDguMDUtMTY0LjAxIDE3OS42OC02NC42ODEgNDYuOTc0LTEzNy44OCAxMTguMDUtMTQyLjk4IDEyOC4wMy01LjkxNTUgMTEuNTg4LTAuMjgyMTYgMS44MTU5LTI2LjQwOC0yNy40NjF6IiBmaWxsPSIjZGQ1MDRmIi8%2BIDwvZz48L3N2Zz4%3D

[release]:https://github.com/MarathonLabs/marathon/releases/latest "Latest Release (external link) ➶"
[docs]:https://docs.marathonlabs.io
[contributing]:https://docs.marathonlabs.io/intro/contribute
[prs]:http://makeapullrequest.com "Make a Pull Request (external link) ➶"
[LICENSE]:https://github.com/MarathonLabs/marathon-gradle-plugin/blob/-/LICENSE

[marathon]:https://github.com/MarathonLabs/marathon/
[plugins-gradle]: https://plugins.gradle.org
