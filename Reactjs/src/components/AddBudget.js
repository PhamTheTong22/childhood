import { useState } from 'react'

const AddBudget = ({ onAdd }) => {
  const [name, setName] = useState('')
  const [amount, setAmount] = useState('')
  const [reminder, setReminder] = useState(false)

  const onSubmit = (e) => {
    e.preventDefault()

    if (!name) {
      alert('Please add a budget')
      return
    }

    onAdd({ name: name, amount: amount, reminder })

    setName('')
    setAmount('')
    setReminder(false)
  }

  return (
    <form className='add-form' onSubmit={onSubmit}>
      <div className='form-control'>
        <label>Budget</label>
        <input
          type='text'
          placeholder='Add Budget'
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label>Amount</label>
        <input
          type='text'
          placeholder='Amount'
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>
      <div className='form-control form-control-check'>
        <label>Set Reminder</label>
        <input
          type='checkbox'
          checked={reminder}
          value={reminder}
          onChange={(e) => setReminder(e.currentTarget.checked)}
        />
      </div>

      <input type='submit' value='Save Budget' className='btn btn-block' />
    </form>
  )
}

export default AddBudget
