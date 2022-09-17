package modal;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;

public class Themes
{
    public static final String[] THEMES_LIST = new String[]{"Arc", "Arc - Orange", "Arc Dark", "Arc Dark - Orange", "Carbon", "Cobalt 2", "Cyan light"};

    public static void setTheme(String string)
    {
        switch (string)
        {
            case "Arc" -> FlatArcIJTheme.setup();
            case "Arc - Orange" -> FlatArcOrangeIJTheme.setup();
            case "Arc Dark" -> FlatArcDarkIJTheme.setup();
            case "Arc Dark - Orange" -> FlatArcDarkOrangeIJTheme.setup();
            case "Carbon" -> FlatCarbonIJTheme.setup();
            case "Cobalt 2" -> FlatCobalt2IJTheme.setup();
            case "Cyan light" -> FlatCyanLightIJTheme.setup();
            case "Dark Flat" -> FlatDarkFlatIJTheme.setup();
            case "Dark purple" -> FlatDarkPurpleIJTheme.setup();
            case "Dracula" -> FlatDraculaIJTheme.setup();
            case "Gradianto Dark Fuchsia" -> FlatGradiantoDarkFuchsiaIJTheme.setup();
            case "Gradianto Deep Ocean" -> FlatGradiantoDeepOceanIJTheme.setup();
            case "Gradianto Midnight Blue" -> FlatGradiantoMidnightBlueIJTheme.setup();
            case "Gradianto Nature Green" -> FlatGradiantoNatureGreenIJTheme.setup();
            case "Gray" -> FlatGrayIJTheme.setup();
            case "Gruvbox Dark Hard" -> FlatGruvboxDarkHardIJTheme.setup();
            case "Gruvbox Dark Medium" -> FlatGruvboxDarkMediumIJTheme.setup();
            case "Gruvbox Dark Soft" -> FlatGruvboxDarkSoftIJTheme.setup();
            case "Hiberbee Dark" -> FlatHiberbeeDarkIJTheme.setup();
            case "High contrast" -> FlatHighContrastIJTheme.setup();
            case "Light Flat" -> FlatLightFlatIJTheme.setup();
            case "Material Design Dark" -> FlatMaterialDesignDarkIJTheme.setup();
            case "Monocai" -> FlatMonocaiIJTheme.setup();
            case "Monokai Pro" -> FlatMonokaiProIJTheme.setup();
            case "Nord" -> FlatNordIJTheme.setup();
            case "One Dark" -> FlatOneDarkIJTheme.setup();
            case "Solarized Dark" -> FlatSolarizedDarkIJTheme.setup();
            case "Solarized Light" -> FlatSolarizedLightIJTheme.setup();
            case "Spacegray" -> FlatSpacegrayIJTheme.setup();
            case "Vuesion" -> FlatVuesionIJTheme.setup();
            case "Xcode-Dark" -> FlatXcodeDarkIJTheme.setup();
            /*Material Theme UI Lite*/
            case "Arc Dark (Material)" ->
                    com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme.setup();
            case "Arc Dark Contrast (Material)" -> FlatArcDarkContrastIJTheme.setup();
            case "Atom One Dark (Material)" -> FlatAtomOneDarkIJTheme.setup();
            case "Atom One Dark Contrast (Material)" -> FlatAtomOneDarkContrastIJTheme.setup();
            case "Atom One Light (Material)" -> FlatAtomOneLightIJTheme.setup();
            case "Atom One Light Contrast (Material)" -> FlatAtomOneLightIJTheme.setup();
            case "Dracula (Material)" ->
                    com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme.setup();
            case "Dracula Contrast (Material)" -> FlatDraculaContrastIJTheme.setup();
            case "GitHub (Material)" -> FlatGitHubIJTheme.setup();
            case "GitHub Contrast (Material)" -> FlatGitHubContrastIJTheme.setup();
            case "GitHub Dark (Material)" -> FlatGitHubDarkIJTheme.setup();
            case "GitHub Dark Contrast (Material)" -> FlatGitHubDarkContrastIJTheme.setup();
            case "Light Owl (Material)" -> FlatLightOwlIJTheme.setup();
            case "Light Owl Contrast (Material)" -> FlatLightOwlContrastIJTheme.setup();
            case "Material Darker (Material)" -> FlatMaterialDarkerIJTheme.setup();
            case "Material Darker Contrast (Material)" -> FlatMaterialDarkerContrastIJTheme.setup();
            case "Material Deep Ocean (Material)" -> FlatMaterialDeepOceanIJTheme.setup();
            case "Material Deep Ocean Contrast (Material)" -> FlatMaterialDeepOceanContrastIJTheme.setup();
            case "Material Lighter (Material)" -> FlatMaterialLighterIJTheme.setup();
            case "Material Lighter Contrast (Material)" -> FlatMaterialLighterContrastIJTheme.setup();
            case "Material Oceanic (Material)" -> FlatMaterialOceanicIJTheme.setup();
            case "Material Oceanic Contrast (Material)" -> FlatMaterialOceanicContrastIJTheme.setup();
            case "Material Palenight (Material)" -> FlatMaterialPalenightIJTheme.setup();
            case "Material Palenight Contrast (Material)" -> FlatMaterialPalenightContrastIJTheme.setup();
            case "Monokai Pro (Material)" ->
                    com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProIJTheme.setup();
            case "Monokai Pro Contrast (Material)" -> FlatMonokaiProContrastIJTheme.setup();
            case "Moonlight (Material)" -> FlatMoonlightIJTheme.setup();
            case "Moonlight Contrast (Material)" -> FlatMoonlightContrastIJTheme.setup();
            case "Night Owl (Material)" -> FlatNightOwlIJTheme.setup();
            case "Night Owl Contrast (Material)" -> FlatNightOwlContrastIJTheme.setup();
            case "Solarized Dark (Material)" ->
                    com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedDarkIJTheme.setup();
            case "Solarized Dark Contrast (Material)" -> FlatSolarizedDarkContrastIJTheme.setup();
            case "Solarized Light (Material)" ->
                    com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatSolarizedLightIJTheme.setup();
            case "Solarized Light Contrast (Material)" -> FlatSolarizedLightContrastIJTheme.setup();
        }
    }
}
