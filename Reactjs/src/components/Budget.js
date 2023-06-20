import { FaTimes } from 'react-icons/fa'

const Budget = ({ budget, onDelete, onToggle }) => {
  return (
    <div
      className={`budget ${budget.reminder && 'reminder'}`}
      onDoubleClick={() => onToggle(budget.id)}
    >
      <h3>
        {budget.name}{' '}
        <FaTimes
          style={{ color: 'red', cursor: 'pointer' }}
          onClick={() => onDelete(budget.id)}
        />
      </h3>
      <p>{budget.amount}</p>
    </div>
  )
}

export default Budget
