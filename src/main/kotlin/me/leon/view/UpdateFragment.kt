package me.leon.view

import javafx.geometry.Pos
import me.leon.REPO_URL
import me.leon.ext.*
import tornadofx.*
import tornadofx.FX.Companion.messages

class UpdateFragment : Fragment(messages["latestVer"]) {
    override val root = hbox {
        prefWidth = DEFAULT_SPACING_40X
        prefHeight = DEFAULT_SPACING_20X
        paddingAll = DEFAULT_SPACING
        spacing = DEFAULT_SPACING_2X
        alignment = Pos.CENTER

        button(messages["upgradeNow"]) { action { action { REPO_URL.openInBrowser() } } }
        button(messages["ingoreOnce"]) { action { close() } }
        button(messages["ingoreForever"]) {
            action {
                Prefs.isIgnoreUpdate = true
                close()
            }
        }
    }
}