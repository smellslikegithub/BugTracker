Vue.component('footer',{
    template:'<h1>This is a heaiding</h1>'
});

new Vue({
    el:"#module1"
});

let app;
app = new Vue({
    el: '#app',
    data: {
        todos: [
            {text: "do this"},
            {text: "do That"}
        ],
        message:"This came from vue",
        generateNumber: () => {
            return Math.random();
        }
    }
});

