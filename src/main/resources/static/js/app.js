let page = 2, size = 5;

let app = new Vue({
    el: '#app',
    data: {
      employees: []
    },
    created(){
        fetch('http://localhost:5000/paginated/' + page + '/' + size)
        .then(response => response.json())
        .then(json => {
            //console.log(json)
            this.employees = json
        })
    }
  })