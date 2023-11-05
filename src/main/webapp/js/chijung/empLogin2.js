'use strict';
(function () {
    HTMLElement.prototype.bubble = function (setting) {
        this.classList.add('bubble-box')
        if (setting && setting.bgColor) {
            document.documentElement.style.setProperty(
                '--background-color',
                setting.bgColor
            )
        }

        for (let i = 1; i <= 40; i++) {
            let bubble = document.createElement('div')
            bubble.classList.add('bubble')
            this.appendChild(bubble)
        }
    }
    document.querySelector('body').bubble()
    // document.querySelector('#app').bubble({
    //     bgColor: '#0e48ad',
    // })
})()
