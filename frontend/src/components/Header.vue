<template>
  <header class="header">
      <div>
        <span class="lab-name">{{title}}</span>
      </div>
      <div>
        <span>
          <a target="_blank" v-bind:href="authorUrl">Цветков Фёдор</a>
          , P3232
        </span>

        <span>
          Вариант
          <a target="_blank" v-bind:href="varUrl">282828</a>
        </span>
      </div>
  </header>
</template>

<script>
export default {
  name: "Header",
  props: {
    title: String,
    authorName: String,
    authorUrl: String,
    varUrl: String
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Bebas+Neue&family=MuseoModerno&display=swap");

header{
  margin: 0;
  padding: 0;
}

.header {
  height: 25%;
  min-width: 440px;
  display: flex;
  background-color: gold;
  justify-content: space-between;
}

/*
  Название проекта:
*/

.header .lab-name{
  color: red;
  font-size: 1.5em;
  font-weight: bold;
  margin-top: 10px;
}

/*
  Имена, Фамилии, вариант лабы:
*/

.header > div {
  display: flex;
  align-items: center;
  margin: 5px 20px 5px;
  flex-direction: column;
}

.header span, .header a{
  color: red;
  font-size: 0.9em;
  font-weight: bold;
  text-decoration: none;
}

</style>