import { Link } from 'react-router-dom'

const About = () => {
  return (
    <div>
      <h4>Version 1.0.0</h4>
      <Link to='/'>
          <button type="button" className={"btn btn-secondary"}>
              Go Back
          </button>
      </Link>
    </div>
  )
}

export default About
