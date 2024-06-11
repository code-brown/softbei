<template>
    <v-table>
      <thead>
        <tr>
          <th class="text-left">
            姓名
          </th>
          <th class="text-left">
            学号
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in desserts"
          :key="item.name"
        >
          <td>{{ item.name }}</td>
          <td>{{ item.calories }}</td>
        </tr>
      </tbody>
    </v-table>
  </template>