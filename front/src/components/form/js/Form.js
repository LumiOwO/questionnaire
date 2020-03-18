function Form (isNew) {
  this.isNew = isNew
  this.title = ''
  this.description = ''
  this.questions = []
  this.onceOnly = false
  this.published = false
  this.repeatDaily = false
  this.userOnly = false
  this.dueDate = Date.now() + 3600 * 24 * 1000
  this.dueTime = Date.now() + 3600 * 24 * 1000
}
export default Form
