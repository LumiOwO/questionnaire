function Question (info, id) {
  this.component = info.component
  this.text = info.text
  this.answers = [1, 2, 3]

  this.hasParent = false
  this.pQuestion = undefined
  this.pAnswers = []
  this.pAnswer = undefined

  this.id = id
}

export default Question
