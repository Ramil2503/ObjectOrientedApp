function open_sidebar() {
    document.getElementById("side_panel_button_text").style.display = "block"
    document.getElementById("side_panel_button_text2").style.display = "block"

    // make close button visible
    document.getElementById("sidebar_close").style.display = "block"
    // make open button not visible
    document.getElementById("sidebar_open").style.display = "none"

    const mediaQuery = window.matchMedia('(max-width: 1199px)');
    if (mediaQuery.matches) {
        document.getElementById("grid_admin").style.gridTemplateColumns = "100%"
        document.getElementById("container").style.display = "none"
        document.getElementById("ordered_mobile").style.display = "block"
        document.getElementById("side_panel_button1").style.display = "block"
        document.getElementById("side_panel_button2").style.display = "block"
        document.getElementById("log-out").style.display = "block"
    } else {
        document.getElementById("grid_admin").style.gridTemplateColumns = "15% 80% 5%"
    }

}
function close_sidebar() {
    document.getElementById("side_panel_button_text").style.display = "none"
    document.getElementById("side_panel_button_text2").style.display = "none"

    // make close button not visible
    document.getElementById("sidebar_close").style.display = "none"
    // make open button visible
    document.getElementById("sidebar_open").style.display = "block"

    const mediaQuery = window.matchMedia('(max-width: 1199px)');
    if (mediaQuery.matches) {
        document.getElementById("grid_admin").style.gridTemplateColumns = "5% 90% 5%"
        document.getElementById("container").style.display = "block"
        document.getElementById("ordered_mobile").style.display = "none"
        document.getElementById("side_panel_button1").style.display = "none"
        document.getElementById("side_panel_button2").style.display = "none"
        document.getElementById("log-out").style.display = "none"
    } else {
        document.getElementById("grid_admin").style.gridTemplateColumns = "5% 90% 5%"
    }

}