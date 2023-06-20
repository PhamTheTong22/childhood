import Budget from './Budget'

const Budgets = ({ budgets, onDelete, onToggle }) => {
  return (
    <>
      {budgets.map((budget, index) => (
        <Budget key={index} budget={budget} onDelete={onDelete} onToggle={onToggle} />
      ))}
    </>
  )
}

export default Budgets
