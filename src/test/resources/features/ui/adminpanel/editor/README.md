# Cucumber Steps Guide. Admin Panel. Editor.
## Overview
Here is a short guide of cucumber steps that can be used to write the tests of Editor page ui and functionality. Here you can find steps and explanation for each one.

## Simple Actions
Its like a "click button", "enter text" e.t.c. The simple actions that can be executed in right place and time and does exactly one action.

### @Given
| Step | Description | Params                                                                |
| ------------- |:-------------|:----------------------------------------------------------------------|
| I opened the admin panel and logged as admin. | Moves to the main admin page | No                                                                    |
| I clicked the {string} button in the left navigation panel. | Moves to the one of pages in left menu bar. | (History-коди \| Партнери \| Едітор \| Команда \| Новини \| Вакансії) |
| I clicked the {string} on the upper tab panel. | Moves to the one of four tabs on the Editor page. | (Categories \| Tags \| Contexts \| Positions) |
| I clicked the {string} add button. | Clicks the big red add button on the different tabs. | ( Додати категорію \| Додати тег \| Додати контекст \| Додати позицію) |
| I clicked the modal {string} close button. | Clicks the close button in the opened modal. | (Any string) |
| I clicked the modal {string} save button. | Clicks the save button in the opened modal. |: (Any string) |
| I filled the modal title field with {string}. | Enters your text into the modal title field. | (Any string)|
| I filled the image field with the image {string}. | Uploads the image in the image field. Only for categories. | (Path to the image) |
|||

### @When
| Step | Description | Params |
| ------------- |:-------------|:-------------|
| I open the admin panel and logged as admin. | Moves to the main admin page | No |
| I click the {string} button in the left navigation panel. | Moves to the one of pages in left menu bar. | (History-коди \| Партнери \| Едітор \| Команда \| Новини \| Вакансії) |
| I click the {string} on the upper tab panel. | Moves to the one of four tabs on the Editor page. | (Categories \| Tags \| Contexts \| Positions) |
| I click the {string} add button. | Clicks the big red add button on the different tabs. | ( Додати категорію \| Додати тег \| Додати контекст \| Додати позицію) |
| I click the modal {string} close button. | Clicks the close button in the opened modal. | (Any string) |
| I click the modal {string} save button. | Clicks the save button in the opened modal. | (Any string) |
| I fill the modal title field with {string}. | Enters your text into the modal title field. | (Any string)|
| I fill the image field with the image {string}. | Uploads the image in the image field. Only for categories. | (Path to the image) |

### @Then
| Step | Description | Params |
| ------------- |:-------------|:-------------|
| list of {string} is displayed. | Checks whether the table is displayed. | (categories \| tags \| contexts \| positions) |
| table has columns {string}. | Checks whether the table has columns. | (String like "column1, column2") |
| modal window is displayed. | Checks whether the modal window is displayed |
| modal has title {string}. | Checks whether the modal window has specific title. | (Any string) |
| table has {string} action. | Checks whether the table row has specific action. | (edit \| delete) |
| ntered title is less than {int} characters. | Checks whether the modal cuted text to the required lenght. | (lenght) |



## Complex Actions
Using it you can make complex logical steps like "create category", "delete category".. And u do not need to think about is all your steps are ok. Also it can be useful when u need some precondition and it is not nessesary to divide it to many steps.
### @Given
| Step | Description                                                                       | Params |
| ------------- |:----------------------------------------------------------------------------------|:-------------|
| I searched the row with {string} name. | Moves to the page with row.                                                       | (Any string) |
| I clicked the {string} action button on the row with {string}}. | Clicks the action on the row with title. Doesnt mater is row on this page or not. | (edit \| delete)(Any string)|
| I created category with name {string}. | Creates the new category.                                                         | (Any string)|
| I created tag with name {string}. | Creates the new tag.                                                              | (Any string)|
| I created context with name {string}. | Creates the new context.                                                          | (Any string)|
| I created position with name {string}. | Creates the new position.                                                         | (Any string)|

### @When
| Step | Description                                                                       | Params |
| ------------- |:----------------------------------------------------------------------------------|:-------------|
| I search the row with {string} name. | Moves to the page with row.                                                       | (Any string) |
| I click the {string} action button on the row with {string}}. | Clicks the action on the row with title. Doesnt mater is row on this page or not. | (edit \| delete)(Any string)|
| I create category with name {string}. | Creates the new category.                                                         | (Any string)|
| I create tag with name {string}. | Creates the new tag.                                                              | (Any string)|
| I create context with name {string}. | Creates the new context.                                                          | (Any string)|
| I create position with name {string}. | Creates the new position.                                                         | (Any string)|

### @Then
| Step | Description | Params |
| ------------- |:-------------|:-------------|
| edited {tab} {old name} is exist with new name {new name}. | Checks whether the edited row were edited. | (Category \| Tag \| Context \| Position)(Any string)(Any string) |
| created {tab} {name} is exist. | Checks whether the new row is exists. | (Category \| Tag \| Context \| Position)(Any string) |
