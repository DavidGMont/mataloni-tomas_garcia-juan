import{u as l}from"./useFetch-8998eb0d.js";import{a as d,T as i}from"./TarjetaOdontologoCuerpo-93a1cbf8.js";import{_,o,c as e,u as a,b as c,F as u,g as p,T as m,d as r}from"./index-6328ba9f.js";const g={key:0},f={key:1},v={__name:"ListarOdontologosView",setup(b){const{data:s,loading:n}=l("/odontologos",{});return(k,y)=>(o(),e("div",null,[a(n)?(o(),e("div",g,"Cargando...")):(o(),e("div",f,[(o(),c(m,{to:"#con"},[(o(!0),e(u,null,p(a(s).data,t=>(o(),e("div",{class:"tarjeta",key:t.id},[r(d),r(i,{nombre:t.nombre,apellido:t.apellido,matricula:t.matricula},null,8,["nombre","apellido","matricula"])]))),128))]))]))]))}},L=_(v,[["__scopeId","data-v-c2b58f79"]]);export{L as default};
